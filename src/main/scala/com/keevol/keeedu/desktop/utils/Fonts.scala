package com.keevol.keeedu.desktop.utils

import javafx.scene.text.Font

object Fonts {
  val customFontName: String = Font.loadFont(getClass.getResourceAsStream("/fonts/SmileySans-Oblique.ttf"), 0).getName // 0 means use system default

  def titleFont(): Font = Font.font(customFontName, 42)

  def subTitleFont(): Font = Font.font(customFontName, 21)

  def defaultFont(): Font = Font.font(customFontName, 21)
}