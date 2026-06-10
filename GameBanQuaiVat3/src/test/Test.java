package test;

import controller.ScreenMainController;
import view.ScreenMain;

public class Test {
	public static void main(String[] args) {
        ScreenMain screenMain = new ScreenMain();
        ScreenMainController mainController = new ScreenMainController(screenMain);
        mainController.startApp();
	}

}
