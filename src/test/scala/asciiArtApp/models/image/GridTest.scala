package asciiArtApp.models.image

import asciiArtApp.models.image.dimensions.Dimensions2D
import asciiArtApp.models.image.grids.Grid
import org.scalatest.FunSuite

class GridTest extends FunSuite {
  test("testGridContentCanNotBeNull") {
    try {
      Grid(null)
      assert(false, "Null allowed")
    }
    catch {
      case e: IllegalArgumentException =>
    }
  }

  test("testGridContentCanNotBeNull2") {
    try {
      new Grid[Int](List(
        List(1, 2),
        null
      ))
      assert(false, "Null allowed")
    }
    catch {
      case e: IllegalArgumentException =>
    }
  }

  test("testGridCanNotBeEmpty1") {
    try {
      new Grid[Int](List.empty)
      assert(false, "Empty allowed")
    }
    catch {
      case e: IllegalArgumentException =>
    }
  }

  test("testGridCanNotBeEmpty2") {
    try {
      new Grid[Int](List(List.empty))
      assert(false, "Empty allowed")
    }
    catch {
      case e: IllegalArgumentException =>
    }
  }

  test("testGridMustBeRectangle") {
    try {
      new Grid[Int](List(
        List(1, 2),
        List(1, 2, 3)
      ))
      assert(false, "Non rectangle allowed")
    }
    catch {
      case e: IllegalArgumentException =>
    }
  }

  test("testSuccessfulInit") {
    try {
      new Grid[Int](List(
        List(1, 2),
        List(1, 2)
      ))
    }
    catch {
      case e: Exception => assert(false, "Initialization was not successful")
    }
  }

  test("testTransform1") {
    val grid = Grid(List(
      List(1, 1, 1),
      List(1, 1, 1)
    ))

    val res = grid.transform(i => i + 1)
    res.elements.flatten.foreach(i => assert(i == 2))
  }

  test("testTransform2") {
    class A
    class B

    val grid = Grid(
      List(
        List(new A, new A, new A, new A, new A),
        List(new A, new A, new A, new A, new A),
        List(new A, new A, new A, new A, new A),
        List(new A, new A, new A, new A, new A),
        List(new A, new A, new A, new A, new A),
        List(new A, new A, new A, new A, new A),
        List(new A, new A, new A, new A, new A),
        List(new A, new A, new A, new A, new A),
        List(new A, new A, new A, new A, new A),
        List(new A, new A, new A, new A, new A),
        List(new A, new A, new A, new A, new A),
        List(new A, new A, new A, new A, new A)
      )
    )

    val result = grid.transform(_ => new B)
    result.elements.flatten.foreach(e => assert(e.isInstanceOf[B]))
  }

  test("testGetDimensions") {
    val grid = Grid(List(
      List(1, 1, 1),
      List(1, 1, 1)
    ))
    assert(grid.getDimensions == Dimensions2D(grid.elements.size, grid.elements.head.size))
  }

  test("testElementsAsList") {
    val grid = Grid(Iterable(
      Iterable(1, 1, 1),
      Iterable(1, 1, 1)
    ))

    val gridList = List(
      List(1, 1, 1),
      List(1, 1, 1)
    )

    assert(grid.getElementsAsList == gridList)
  }
}
