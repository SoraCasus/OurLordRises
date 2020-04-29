package assets;

import com.soracasus.tilegame.assets.Assets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

public class TestAssets {


	@Test
	void testLoadTexture () {
		BufferedImage image = Assets.getInstance().getTexture("/test.png");
		Assertions.assertNotNull(image);

		Assertions.assertThrows(IllegalArgumentException.class,
				() -> Assets.getInstance().getTexture("/not_a_test.png"));

	}


}
