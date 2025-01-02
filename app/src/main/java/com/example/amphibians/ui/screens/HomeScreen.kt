package com.example.amphibians.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.amphibians.R
import com.example.amphibians.network.Amphibian
import com.example.amphibians.ui.AmphibiansUiState
import com.example.amphibians.ui.theme.AmphibiansTheme

@Composable
fun HomeScreen(
    amphibiansUiState: AmphibiansUiState,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    when (amphibiansUiState) {
        is AmphibiansUiState.Error -> ErrorScreen(modifier.fillMaxSize())
        is AmphibiansUiState.Loading -> LoadingScreen(modifier.fillMaxSize())
        is AmphibiansUiState.Success -> AmphibiansListScreen(
            amphibiansList = amphibiansUiState.amphibians,
            modifier = modifier,
            contentPadding = contentPadding
        )
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = Modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.ic_connection_error),
            contentDescription = stringResource(R.string.connection_error)
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
    }
}

@Composable
fun AmphibiansListScreen(
    amphibiansList: List<Amphibian>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    Text(text = amphibiansList.toString(), modifier = Modifier.padding(16.dp))
}

@Preview(showBackground = true)
@Composable
private fun LoadingScreenPreview() {
    AmphibiansTheme {
        LoadingScreen()
    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorScreenPreview() {
    AmphibiansTheme {
        ErrorScreen()
    }
}