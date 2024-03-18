package com.micudasoftware.data.userprofile.repository

import com.micudasoftware.data.userprofile.api.dto.GetUserProfileResponseDto
import com.micudasoftware.data.userprofile.api.dto.UpdateUserProfileRequestDto
import com.micudasoftware.domain.userprofile.model.UserProfile

fun GetUserProfileResponseDto.toUserProfile() =
    UserProfile(
        userId = userId,
        userName = userName,
        aboutMeText = aboutMeText,
        titlePhotoUrl = titlePhotoUrl,
        profilePhotoUrl = profilePhotoUrl,
    )

fun UserProfile.toDto() =
    UpdateUserProfileRequestDto(
        userName = userName,
        aboutMeText = aboutMeText,
        titlePhotoUrl = titlePhotoUrl,
        profilePhotoUrl = profilePhotoUrl,
    )