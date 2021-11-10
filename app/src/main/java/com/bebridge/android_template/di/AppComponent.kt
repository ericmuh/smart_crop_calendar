package com.bebridge.android_template.di

import com.bebridge.android_template.MainActivity
import com.bebridge.android_template.fragment.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

  fun inject(activity: MainActivity)

  fun inject(fragment: HomeFragment)

  fun inject(fragment: MyPageFragment)

  fun inject(fragment: MenuFragment)

  fun inject(fragment: LoginFragment)

  fun inject(fragment: SignUpFragment)

  fun inject(fragment: EditProfileFragment)

  fun inject(fragment: ChangePasswordFragment)

  fun inject(fragment: ConfirmNumberFragment)

  fun inject(fragment: DetailFragment)

  fun inject(fragment: AddFarmFragment)

  fun inject(fragment: SoilFragment)

  fun inject(fragment: IrrigationFragment)

  fun inject(fragment: FarmDetailsFragment)

  fun inject(fragment: NotificationFragment)

  fun inject(fragment: FieldViewFragment)

  fun inject(fragment: SettingsFragment)
}