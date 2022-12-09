package cz.mendelu.water_balance

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import cz.mendelu.water_balance.databinding.ViewInfoBinding
import cz.mendelu.water_balance.databinding.ViewNormOfWaterBinding

class NormOfWaterView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttrs: Int = 0
) : FrameLayout(context, attrs, defStyleAttrs)
{
    init {
        init(context, attrs)
    }

    private lateinit var binding: ViewNormOfWaterBinding

    var value: String
        get() = binding.norm.text.toString()
        set(value){
            binding.norm.setText(value)
        }

    private fun init(context: Context, attrs: AttributeSet?){
        binding = ViewNormOfWaterBinding.inflate(LayoutInflater.from(context), this, true)
        if(attrs != null){
            loadAttributes(attrs)
        }
    }

    private fun loadAttributes(attrs: AttributeSet){
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.InfoView)
        val value = attributes.getString(R.styleable.InfoView_value)
        val valueText = attributes.getString(R.styleable.InfoView_valueText)
        val image = attributes.getDrawable(R.styleable.InfoView_image)

        binding.norm.text = value
        binding.normText.text = valueText
        binding.normOfWaterImage.setImageDrawable(image)


    }



}