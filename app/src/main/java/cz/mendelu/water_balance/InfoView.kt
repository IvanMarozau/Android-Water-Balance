package cz.mendelu.water_balance

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import cz.mendelu.water_balance.databinding.ViewInfoBinding

class InfoView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttrs: Int = 0
) : FrameLayout(context, attrs, defStyleAttrs)
{
    init {
        init(context, attrs)
    }

    private lateinit var binding: ViewInfoBinding

    var value: String
        get() = binding.value.text.toString()
        set(value){
            binding.value.setText(value)
        }

    private fun init(context: Context, attrs: AttributeSet?){
        binding = ViewInfoBinding.inflate(LayoutInflater.from(context), this, true)
        if(attrs != null){
            loadAttributes(attrs)
        }
    }

    private fun loadAttributes(attrs: AttributeSet){
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.InfoView)
        val valueText = attributes.getString(R.styleable.InfoView_valueText)
        val value = attributes.getString(R.styleable.InfoView_value)
        val image = attributes.getDrawable(R.styleable.InfoView_image)

        binding.value.text = value
        binding.valueText.text = valueText
        binding.infoImage.setImageDrawable(image)


    }

    fun setOnClearClickListenner(listener: OnClickListener){
        binding.editButton.setOnClickListener(listener)
    }



}