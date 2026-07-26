import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.linkdevelopment.android_tasks.presentation.ui.posts.screen.DetailsScreen
import com.linkdevelopment.android_tasks.presentation.ui.posts.screen.PostsScreen
import com.linkdevelopment.android_tasks.presentation.ui.utils.Routes


@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Posts
    ) {

        composable<Routes.Posts> {

            PostsScreen(
                onNavigateToDetails = { post ->
                    navController.navigate(
                        Routes.Details(
                            id = post.id,
                            title = post.title,
                            content = post.content,
                            image = post.image
                        )
                    )
                }
            )

        }

        composable<Routes.Details> { backStackEntry ->

            val args = backStackEntry.toRoute<Routes.Details>()

            DetailsScreen(
                id = args.id,
                title = args.title,
                content = args.content,
                image = args.image
            )
        }

    }
}
