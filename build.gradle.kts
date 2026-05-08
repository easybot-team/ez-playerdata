plugins {
    id("java-library")
    `maven-publish`
}

group = "com.springwater.playerdata"
version = "1.0-rc.0"

repositories {
    mavenCentral()
    maven("https://jitpack.io/", { name = "Jitpack" })
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    api("com.github.Querz:NBT:6.1")

    compileOnly("org.projectlombok:lombok:1.18.32")
    annotationProcessor("org.projectlombok:lombok:1.18.32")
    implementation("org.jetbrains:annotations:24.0.0")

    implementation("com.google.code.gson:gson:2.10")
    implementation("net.kyori:adventure-api:4.25.0")
    implementation("net.kyori:adventure-text-serializer-legacy:4.25.0")
    implementation("net.kyori:adventure-text-serializer-gson:4.25.0")
}

tasks.test {
    useJUnitPlatform()
}


publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/easybot-team/ez-playerdata")

            credentials {
                username = project.findProperty("gpr.user") as String?
                    ?: System.getenv("USERNAME")

                password = project.findProperty("gpr.key") as String?
                    ?: System.getenv("TOKEN")
            }
        }
    }

    publications {
        create<MavenPublication>("gpr") {
            from(components["java"])
        }
    }
}