import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BouncingBall extends Application {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int CYLINDER_RADIUS = 50;
    private static final int CYLINDER_HEIGHT = 200;
    private TranslateTransition dropTransition;
    private TranslateTransition moveTowardsMouseTransition;
    private RotateTransition rotateTransition;
    private RotateTransition rotateTransition2;
    Group group, group2,center;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Creating a cylinder at the center
        Cylinder cylinder = new Cylinder(CYLINDER_RADIUS, CYLINDER_HEIGHT);
        cylinder.setTranslateX(WIDTH / 2.0);
        cylinder.setTranslateY(HEIGHT / 2.0 - 50);

        // Applying material to the cylinder
        PhongMaterial cylinderMaterial = new PhongMaterial();
        cylinderMaterial.setDiffuseColor(Color.GREEN);
        cylinder.setMaterial(cylinderMaterial);

        // Creating a circle
        Circle circle = new Circle(50);
        circle.setTranslateY(100); // Adjust the circle position above the cylinder
        circle.setTranslateX(400);

        // Applying materials to the shapes
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(Color.BLUE); // Set color for the cylinder
        cylinder.setMaterial(material);


        // Creating butterfly images
        Image butterfly1 = new Image(getClass().getResourceAsStream("butterfly.jpeg"), 100, 100, false, false);
        Image butterfly2 = new Image(getClass().getResourceAsStream("butterfly1.jpeg"), 100, 100, false, false);
        Image butterfly3 = new Image(getClass().getResourceAsStream("butterfly.jpeg"), 100, 100, false, false);

        ImageView imageView1 = createButterflyImageView(butterfly1, 0);
        ImageView imageView2 = createButterflyImageView(butterfly2, 120);
        ImageView imageView3 = createButterflyImageView(butterfly3, 240);
        imageView1.setTranslateX(220);
        imageView1.setTranslateY(270);
        imageView2.setTranslateX(320);
        imageView2.setTranslateY(170);
        imageView3.setTranslateX(440);
        imageView3.setTranslateY(270);
        AnimationTimer timer1 = new AnimationTimer() {
            @Override
            public void handle(long arg0) {
                // Rotate sun point group
                imageView1.setRotate(imageView1.getRotate() + 1);
                imageView2.setRotate(imageView2.getRotate() + 1);
                imageView3.setRotate(imageView3.getRotate() + 1);
            }
        };
        timer1.start();
        // Creating a group to hold the cylinder and butterfly images
        // Creating a group to hold both shapes
         center = new Group(cylinder, circle);

        Group group = new Group();
        // Prepare group sources
        // 1. Ambient group
        AmbientLight boardLight = new AmbientLight(Color.YELLOW);
        // 2. Point group
        PointLight sunLight = new PointLight();
        sunLight.getTransforms().add(new Translate(350, 100, 0)); // Move point group to a visible location
        // 2.1 Create a sphere representing the sun
        Sphere sunSphere = new Sphere(30);
        sunSphere.getTransforms().add(new Translate(350, 100, 0)); // Move sphere to a visible location
        sunSphere.setMaterial(new PhongMaterial(Color.GOLD));
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long arg0) {
                // Rotate sun point group
                sunLight.setRotationAxis(Rotate.Y_AXIS);
                sunLight.setRotate(sunLight.getRotate() + 1.1); // Rotate sun sphere shape
                sunSphere.setRotationAxis(Rotate.Y_AXIS);
                sunSphere.setRotate(sunSphere.getRotate() + 1.1); // Rotate earth
                group.setRotationAxis(Rotate.Y_AXIS);
                group.setRotate(center.getRotate() + 0.1);
            }
        };
        timer.start();
        group.getChildren().addAll(getBackground());
        group.getChildren().addAll(sunLight, sunSphere, boardLight);


        Group root2 = new Group();
        root2.getChildren().addAll(group, imageView1, imageView2, imageView3, center);

        Group root = new Group(root2);

        // Creating a scene
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        // Set up a TranslateTransition for dropping the rear wheel
        dropTransition = new TranslateTransition(Duration.seconds(1), center);
        dropTransition.setToY(HEIGHT - 400); // Drop to the floor
        dropTransition.setOnFinished(event -> dropTransition.stop());

        // Set up keyboard and mouse event handling
        scene.setOnKeyPressed(event -> KeyPress(event.getCode()));

        // Setting stage properties
        primaryStage.setTitle("Butterfly Circle Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void KeyPress(KeyCode code) {
        switch (code) {
            case D:
                drop();
                break;
            case R:
                resetScene();
                break;
            case LEFT:
                rotateLeft();
                break;
            case RIGHT:
                rotateRight();
                break;
        }
    }

    private ImageView getBackground() {
        // Prepare and set background image
        Image image = new Image(getClass().getResourceAsStream("chess.jpeg"), WIDTH, HEIGHT, false, false); // Get BG image
        ImageView imageView = new ImageView(image); // Add image Node for painting the loaded image
        imageView.getTransforms().add(new Translate(0, 0, 0)); // Translate to top-left corner
        return imageView;
    }

    private ImageView createButterflyImageView(Image image, double angle) {
        ImageView imageView = new ImageView(image);
        imageView.setRotate(angle);
        imageView.setTranslateX(WIDTH / 2.0);
        imageView.setTranslateY(HEIGHT / 2.0 - CYLINDER_HEIGHT / 2.0 - image.getHeight() / 2.0);
        return imageView;
    }


    private void drop() {
        dropTransition.play();
    }

    private void rotateLeft() {
        center.setRotate(center.getRotate() - 15);
    }

    private void rotateRight() {
        center.setRotate(center.getRotate() + 15);
    }

    private void resetScene() {
        dropTransition.stop();

        // Reset translations and rotations
        center.setTranslateX(0);
        center.setTranslateY(0);
        center.setRotate(0);
    }
}
