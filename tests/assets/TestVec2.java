package assets;

import com.soracasus.tilegame.math.Vec2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestVec2 {

	private Vec2 vec1;
	private Vec2 vec2;

	@BeforeEach
	void setup () {
		vec1 = new Vec2();
		vec2 = new Vec2(2);
	}

	@Test
	void testAdd () {
		Vec2 v = vec1.add(vec2);
		Assertions.assertEquals(2, v.getX());
		Assertions.assertEquals(2, v.getY());

		v = vec2.add(vec2);
		Assertions.assertEquals(4, v.getX());
		Assertions.assertEquals(4, v.getY());
	}

	@Test
	void testSub () {
		Vec2 v = vec1.sub(vec2);
		Assertions.assertEquals(-2, v.getX());
		Assertions.assertEquals(-2, v.getY());

		v = vec2.sub(vec2);
		Assertions.assertEquals(0, v.getX());
		Assertions.assertEquals(0, v.getY());
	}

	@Test
	void testMul () {
		Vec2 v = vec1.mul(vec2);
		Assertions.assertEquals(0, v.getX());
		Assertions.assertEquals(0, v.getY());

		v = vec2.mul(vec2);
		Assertions.assertEquals(4, v.getX());
		Assertions.assertEquals(4, v.getY());

		v = vec2.mul(5);
		Assertions.assertEquals(10, v.getX());
		Assertions.assertEquals(10, v.getY());
	}

	@Test
	void testDot () {
		Vec2 vec3 = new Vec2();
		vec3.setX(1);
		vec3.setY(1);

		vec3.normalize();
		vec2.normalize();

		Assertions.assertEquals(1, vec2.dot(vec3), 0.001F);
	}


}
