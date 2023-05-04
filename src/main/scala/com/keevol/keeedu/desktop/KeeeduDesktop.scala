package com.keevol.keeedu.desktop

import com.keevol.keeedu.desktop.utils.Fonts
import javafx.application.Application
import javafx.beans.value.{ChangeListener, ObservableValue}
import javafx.concurrent.Worker
import javafx.concurrent.Worker.State
import javafx.geometry.{Insets, Pos}
import javafx.scene.Scene
import javafx.scene.control.{Button, Label, TextField}
import javafx.scene.layout._
import javafx.scene.text.Font
import javafx.scene.web.WebView
import javafx.stage.Stage


class KeeeduDesktop extends Application {
  override def start(stage: Stage): Unit = {
    val pane = new BorderPane()
    val scene = new Scene(pane)
    scene.getStylesheets.add(getClass.getResource("/css/style.css").toExternalForm)

    createAuthView(scene, pane)

    stage.setScene(scene)
    stage.setFullScreen(true)
    stage.setAlwaysOnTop(true)
    stage.setTitle("福强进学堂")

    stage.show()
  }

  private def createAuthView(scene: Scene, container: BorderPane) = {
    // -fx-background-position: top left; -fx-background-repeat: no-repeat;
    container.setStyle(s"""-fx-background-image: url("/images/KEEVOL_Slogan.jpg"); -fx-background-repeat: stretch; -fx-background-position: center bottom;""")

    val banner = new Label("福强进学堂")
    banner.setFont(Fonts.titleFont())
    val bannerLayout = new HBox(20)
    bannerLayout.setPadding(new Insets(20))
    bannerLayout.getChildren.addAll(HBoxEmptyPadding(), banner, HBoxEmptyPadding())
    container.setTop(bannerLayout)

    val label = new Label("请输入您收到的来自福强的访问链接（Enter MagicLink Here）：")
    label.setFont(Fonts.subTitleFont())

    val input = new TextField()
    input.setFont(Fonts.defaultFont())
    val button = new Button("确认")
    button.setFont(Fonts.defaultFont())
    button.setMinWidth(200)
    button.setMinHeight(36)
    button.setOnAction(_ => {
      if (!(input.getText == null || input.getText.isEmpty)) {
        switchToWebView(scene, input.getText.trim)
      }
    })

    val layout = new VBox(20)
    layout.setSpacing(10)
    layout.setPadding(new Insets(20))
    layout.setAlignment(Pos.CENTER_LEFT)
    layout.getChildren.addAll(label, input, button)
    container.setCenter(layout)


    val footer = new Label("Credit to @fujohnwang, https://afoo.me")
    footer.setFont(Font.font(Fonts.customFontName, 11))
    val footerLayout = new HBox(20)
    footerLayout.setPadding(new Insets(20))
    footerLayout.getChildren.addAll(HBoxEmptyPadding(), footer, HBoxEmptyPadding())
    container.setBottom(footerLayout)
  }

  private def switchToWebView(scene: Scene, url: String): Unit = {
    val webv = new WebView
    webv.setContextMenuEnabled(false)
    webv.getEngine.getLoadWorker.stateProperty().addListener(new ChangeListener[State] {
      override def changed(observableValue: ObservableValue[_ <: State], oldState: State, newState: State): Unit = {
        if (newState == Worker.State.SUCCEEDED) {
          //          println(s"url:${webv.getEngine.getLocation} loaded successfully.")
        }

      }
    })
    webv.getEngine.load(url)
    scene.setRoot(webv)
  }
}

class HBoxEmptyPadding extends Region {
  HBox.setHgrow(this, Priority.ALWAYS)
}

object HBoxEmptyPadding {
  def apply(): HBoxEmptyPadding = new HBoxEmptyPadding()
}

object KeeeduDesktop {
  def main(args: Array[String]): Unit = {
    Application.launch(classOf[KeeeduDesktop], args: _*)
  }
}

object KeeeduDesktopLauncher {
  def main(args: Array[String]): Unit = {
    KeeeduDesktop.main(args)
  }
}

