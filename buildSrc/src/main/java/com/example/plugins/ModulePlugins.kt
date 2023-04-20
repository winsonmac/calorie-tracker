package com.example.plugins

class CorePlugin : BaseModulePlugin("com.example.core", false)
class CoreUiPlugin : BaseModulePlugin("com.example.core_ui", true)
class OnBoardingDomainPlugin : BaseModulePlugin("com.example.onboarding_domain", false)
class OnBoardingPresentationPlugin : BaseModulePlugin("com.example.onboarding_presentation", true)
class TrackerDataPlugin : BaseModulePlugin("com.example.tracker_data", false)
class TrackerDomainPlugin : BaseModulePlugin("com.example.tracker_domain", false)
class TrackerPresentationPlugin : BaseModulePlugin("com.example.tracker_presentation", true)