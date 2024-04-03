package com.example.tamingtemper.ui

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.tamingtemper.R
import com.example.tamingtemper.common.BaseFragment
import com.example.tamingtemper.databinding.FragmentJourneyBinding
import com.example.tamingtemper.databinding.ItemLayoutLevelBinding
import com.example.tamingtemper.viewmodel.JourneyViewModel
import com.example.tamingtemper.vo.Level
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class JourneyFragment: BaseFragment(R.layout.fragment_journey) {

    companion object {
        val TAG: String = "JourneyFragment"
    }

    private val vm: JourneyViewModel by viewModels()

    private lateinit var binding: FragmentJourneyBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentJourneyBinding.bind(view)

        with(binding) {
            rbMon.isChecked = true
            line1.isSelected = true
        }

        getLevels()
        events()

    }

    private fun getLevels(){
        lifecycleScope.launch {
            vm.getLevels().onSuccess {
                it.levels?.forEach { level ->
                    binding.layLevel.addView(addLevel(level, binding.layLevel))
                }
            }
        }
    }

    private fun addLevel(data: Level?, parent: ViewGroup?): View {
        val bind = ItemLayoutLevelBinding.inflate(layoutInflater, parent, false)
        bind.lblLevel.text = "Level ${data?.level}"
        bind.lblTitle.text = data?.title
        bind.lblDesc.text = data?.description
        val aaAdapter = ActivitiesAdapter()
        bind.rvActivities.apply {
            layoutManager = FlexboxLayoutManager(context).apply {
                justifyContent = JustifyContent.CENTER
                alignItems = AlignItems.CENTER
                flexDirection = FlexDirection.ROW
                flexWrap = FlexWrap.WRAP
            }
            adapter = aaAdapter
        }
        aaAdapter.submitList(data?.activities)
        return bind.root
    }

    private fun events(){
        binding.apply {
            rbMon.setOnClickListener {
               daysSelection(
                   mon = true,
                   tues = false,
                   wed = false,
                   thu = false,
                   fri = false,
                   sat = false,
                   sun = false)
            }
            rbTues.setOnClickListener {
                daysSelection(
                    mon = false,
                    tues = true,
                    wed = false,
                    thu = false,
                    fri = false,
                    sat = false,
                    sun = false)
            }
            rbWed.setOnClickListener {
                daysSelection(
                    mon = false,
                    tues = false,
                    wed = true,
                    thu = false,
                    fri = false,
                    sat = false,
                    sun = false)
            }
            rbThu.setOnClickListener {
                daysSelection(
                    mon = false,
                    tues = false,
                    wed = false,
                    thu = true,
                    fri = false,
                    sat = false,
                    sun = false)
            }
            rbFri.setOnClickListener {
                daysSelection(
                    mon = false,
                    tues = false,
                    wed = false,
                    thu = false,
                    fri = true,
                    sat = false,
                    sun = false)
            }
            rbSat.setOnClickListener {
                daysSelection(
                    mon = false,
                    tues = false,
                    wed = false,
                    thu = false,
                    fri = false,
                    sat = true,
                    sun = false)
            }
            rbSun.setOnClickListener {
                daysSelection(
                    mon = false,
                    tues = false,
                    wed = false,
                    thu = false,
                    fri = false,
                    sat = false,
                    sun = true)
            }
        }
    }

    private fun daysSelection(mon: Boolean, tues: Boolean, wed: Boolean, thu: Boolean, fri: Boolean,
                              sat: Boolean, sun: Boolean){
        binding.rbMon.isChecked = mon
        binding.rbTues.isChecked = tues
        binding.rbWed.isChecked = wed
        binding.rbThu.isChecked = thu
        binding.rbFri.isChecked = fri
        binding.rbSat.isChecked = sat
        binding.rbSun.isChecked = sun
        binding.line1.isSelected = mon
        binding.line2.isSelected = tues
        binding.line3.isSelected = wed
        binding.line4.isSelected = thu
        binding.line5.isSelected = fri
        binding.line6.isSelected = sat
        binding.line7.isSelected = sun
    }
}