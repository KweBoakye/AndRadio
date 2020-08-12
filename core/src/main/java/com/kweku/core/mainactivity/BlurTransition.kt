package com.kweku.core.mainactivity


import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.MotionScene
import android.content.Context
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import androidx.constraintlayout.motion.widget.TransitionBuilder
import androidx.constraintlayout.widget.ConstraintSet
import com.kweku.core.R

class BlurTransition: MotionLayout{


    constructor(context: Context): super(context)


    constructor(context: Context, attrs: AttributeSet?): super(context, attrs)


    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int):
            super(context, attrs, defStyleAttr)



    private var imageTranstition: MotionScene.Transition? = null




    override fun onFinishInflate() {
        super.onFinishInflate()

        val scene = MotionScene(this)
        imageTranstition = createTransition(scene)
        scene.addTransition(imageTranstition)
        scene.setTransition(imageTranstition)
        setScene(scene)
    }


    private fun createTransition(scene: MotionScene): MotionScene.Transition {
        val startSetId = View.generateViewId()
        val startSet = ConstraintSet()
        startSet.setTranslationY(R.id.background_image, height.toFloat())
        startSet.clone(this)
        val endSetId = View.generateViewId()
        val endSet = ConstraintSet()
        endSet.setTranslationY(R.id.background_image, -height.toFloat())
        endSet.clone(this)
        val transitionId = View.generateViewId()
        return TransitionBuilder.buildTransition(
                scene,
                transitionId,
                startSetId, startSet,
                endSetId, endSet)
    }

    fun animateImage(){
        val startSet: ConstraintSet = getConstraintSet(imageTranstition!!.startConstraintSetId)
        val endSet: ConstraintSet = getConstraintSet(imageTranstition!!.endConstraintSetId)

        setTransition(imageTranstition!!.startConstraintSetId, imageTranstition!!.endConstraintSetId)
        transitionToEnd()

        // Update the end state to be the current.
        startSet.clone(endSet)
    }
}