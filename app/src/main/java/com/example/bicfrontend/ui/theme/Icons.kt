package com.example.bicfrontend.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val Add: ImageVector
    get() {
        if (_Add != null) {
            return _Add!!
        }
        _Add = ImageVector.Builder(
            name = "Add",
            defaultWidth = 16.dp,
            defaultHeight = 16.dp,
            viewportWidth = 16f,
            viewportHeight = 16f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(14f, 7f)
                verticalLineToRelative(1f)
                horizontalLineTo(8f)
                verticalLineToRelative(6f)
                horizontalLineTo(7f)
                verticalLineTo(8f)
                horizontalLineTo(1f)
                verticalLineTo(7f)
                horizontalLineToRelative(6f)
                verticalLineTo(1f)
                horizontalLineToRelative(1f)
                verticalLineToRelative(6f)
                horizontalLineToRelative(6f)
                close()
            }
        }.build()
        return _Add!!
    }

private var _Add: ImageVector? = null

public val Trash: ImageVector
    get() {
        if (_Trash != null) {
            return _Trash!!
        }
        _Trash = ImageVector.Builder(
            name = "Trash",
            defaultWidth = 16.dp,
            defaultHeight = 16.dp,
            viewportWidth = 16f,
            viewportHeight = 16f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(10f, 3f)
                horizontalLineToRelative(3f)
                verticalLineToRelative(1f)
                horizontalLineToRelative(-1f)
                verticalLineToRelative(9f)
                lineToRelative(-1f, 1f)
                horizontalLineTo(4f)
                lineToRelative(-1f, -1f)
                verticalLineTo(4f)
                horizontalLineTo(2f)
                verticalLineTo(3f)
                horizontalLineToRelative(3f)
                verticalLineTo(2f)
                arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, 1f, -1f)
                horizontalLineToRelative(3f)
                arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, 1f, 1f)
                verticalLineToRelative(1f)
                close()
                moveTo(9f, 2f)
                horizontalLineTo(6f)
                verticalLineToRelative(1f)
                horizontalLineToRelative(3f)
                verticalLineTo(2f)
                close()
                moveTo(4f, 13f)
                horizontalLineToRelative(7f)
                verticalLineTo(4f)
                horizontalLineTo(4f)
                verticalLineToRelative(9f)
                close()
                moveToRelative(2f, -8f)
                horizontalLineTo(5f)
                verticalLineToRelative(7f)
                horizontalLineToRelative(1f)
                verticalLineTo(5f)
                close()
                moveToRelative(1f, 0f)
                horizontalLineToRelative(1f)
                verticalLineToRelative(7f)
                horizontalLineTo(7f)
                verticalLineTo(5f)
                close()
                moveToRelative(2f, 0f)
                horizontalLineToRelative(1f)
                verticalLineToRelative(7f)
                horizontalLineTo(9f)
                verticalLineTo(5f)
                close()
            }
        }.build()
        return _Trash!!
    }

private var _Trash: ImageVector? = null

public val Search: ImageVector
    get() {
        if (_Search != null) {
            return _Search!!
        }
        _Search = ImageVector.Builder(
            name = "Search",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(15.25f, 0f)
                arcToRelative(8.25f, 8.25f, 0f, isMoreThanHalf = false, isPositiveArc = false, -6.18f, 13.72f)
                lineTo(1f, 22.88f)
                lineToRelative(1.12f, 1f)
                lineToRelative(8.05f, -9.12f)
                arcTo(8.251f, 8.251f, 0f, isMoreThanHalf = true, isPositiveArc = false, 15.25f, 0.01f)
                verticalLineTo(0f)
                close()
                moveToRelative(0f, 15f)
                arcToRelative(6.75f, 6.75f, 0f, isMoreThanHalf = true, isPositiveArc = true, 0f, -13.5f)
                arcToRelative(6.75f, 6.75f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0f, 13.5f)
                close()
            }
        }.build()
        return _Search!!
    }

private var _Search: ImageVector? = null

public val Home: ImageVector
    get() {
        if (_Home != null) {
            return _Home!!
        }
        _Home = ImageVector.Builder(
            name = "Home",
            defaultWidth = 16.dp,
            defaultHeight = 16.dp,
            viewportWidth = 16f,
            viewportHeight = 16f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(8.36f, 1.37f)
                lineToRelative(6.36f, 5.8f)
                lineToRelative(-0.71f, 0.71f)
                lineTo(13f, 6.964f)
                verticalLineToRelative(6.526f)
                lineToRelative(-0.5f, 0.5f)
                horizontalLineToRelative(-3f)
                lineToRelative(-0.5f, -0.5f)
                verticalLineToRelative(-3.5f)
                horizontalLineTo(7f)
                verticalLineToRelative(3.5f)
                lineToRelative(-0.5f, 0.5f)
                horizontalLineToRelative(-3f)
                lineToRelative(-0.5f, -0.5f)
                verticalLineTo(6.972f)
                lineTo(2f, 7.88f)
                lineToRelative(-0.71f, -0.71f)
                lineToRelative(6.35f, -5.8f)
                horizontalLineToRelative(0.72f)
                close()
                moveTo(4f, 6.063f)
                verticalLineToRelative(6.927f)
                horizontalLineToRelative(2f)
                verticalLineToRelative(-3.5f)
                lineToRelative(0.5f, -0.5f)
                horizontalLineToRelative(3f)
                lineToRelative(0.5f, 0.5f)
                verticalLineToRelative(3.5f)
                horizontalLineToRelative(2f)
                verticalLineTo(6.057f)
                lineTo(8f, 2.43f)
                lineTo(4f, 6.063f)
                close()
            }
        }.build()
        return _Home!!
    }

private var _Home: ImageVector? = null