USE [CRUDMETAPHORCE]
GO
/****** Object:  Table [dbo].[Estatus_Cat]    Script Date: 28/03/2024 05:45:33 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Estatus_Cat]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[Estatus_Cat](
	[IdEstatus] [int] IDENTITY(1,1) NOT NULL,
	[Descripcion_Estatus] [varchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[IdEstatus] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
END
GO
/****** Object:  Table [dbo].[Tareas]    Script Date: 28/03/2024 05:45:34 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Tareas]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[Tareas](
	[IdTarea] [int] IDENTITY(1,1) NOT NULL,
	[DescripcionTarea] [varchar](max) NULL,
	[IdEstatus] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[IdTarea] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
END
GO
/****** Object:  Table [dbo].[Users]    Script Date: 28/03/2024 05:45:34 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Users]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[Users](
	[IdUser] [int] IDENTITY(1,1) NOT NULL,
	[UserName] [varchar](55) NULL,
	[Role] [varchar](10) NULL,
	[Password] [varchar](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[IdUser] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
END
GO
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK__Tareas__IdEstatu__4BAC3F29]') AND parent_object_id = OBJECT_ID(N'[dbo].[Tareas]'))
ALTER TABLE [dbo].[Tareas]  WITH CHECK ADD FOREIGN KEY([IdEstatus])
REFERENCES [dbo].[Estatus_Cat] ([IdEstatus])
GO
/****** Object:  StoredProcedure [dbo].[pa_d_BorrarTarea]    Script Date: 28/03/2024 05:45:34 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[pa_d_BorrarTarea]') AND type in (N'P', N'PC'))
BEGIN
EXEC dbo.sp_executesql @statement = N'CREATE PROCEDURE [dbo].[pa_d_BorrarTarea] AS' 
END
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
ALTER PROCEDURE [dbo].[pa_d_BorrarTarea] 
	-- Add the parameters for the stored procedure here
	@Id INT
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	DELETE FROM Tareas WHERE IdTarea = @Id
END
GO
/****** Object:  StoredProcedure [dbo].[pa_i_CrearTarea]    Script Date: 28/03/2024 05:45:34 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[pa_i_CrearTarea]') AND type in (N'P', N'PC'))
BEGIN
EXEC dbo.sp_executesql @statement = N'CREATE PROCEDURE [dbo].[pa_i_CrearTarea] AS' 
END
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
ALTER PROCEDURE [dbo].[pa_i_CrearTarea] 
	-- Add the parameters for the stored procedure here
	@Descripcion VARCHAR(MAX),
	@IdEstatus INT
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	INSERT INTO Tareas(DescripcionTarea, IdEstatus)VALUES( @Descripcion, @IdEstatus);
END
GO
/****** Object:  StoredProcedure [dbo].[pa_i_RegistrarUsuario]    Script Date: 28/03/2024 05:45:34 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[pa_i_RegistrarUsuario]') AND type in (N'P', N'PC'))
BEGIN
EXEC dbo.sp_executesql @statement = N'CREATE PROCEDURE [dbo].[pa_i_RegistrarUsuario] AS' 
END
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
ALTER PROCEDURE [dbo].[pa_i_RegistrarUsuario]
	-- Add the parameters for the stored procedure here
	@UserName VARCHAR(MAX),
	@Password VARCHAR(MAX),
	@Role VARCHAR(MAX)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	INSERT INTO Users(UserName, Password, Role) VALUES(@UserName, @Password, @Role)
END
GO
/****** Object:  StoredProcedure [dbo].[pa_s_ObtenerTareaPorId]    Script Date: 28/03/2024 05:45:34 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[pa_s_ObtenerTareaPorId]') AND type in (N'P', N'PC'))
BEGIN
EXEC dbo.sp_executesql @statement = N'CREATE PROCEDURE [dbo].[pa_s_ObtenerTareaPorId] AS' 
END
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
ALTER PROCEDURE [dbo].[pa_s_ObtenerTareaPorId]
	-- Add the parameters for the stored procedure here
	@IdTarea INT
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	SELECT T.IdTarea, T.DescripcionTarea, T.IdEstatus, E.Descripcion_Estatus
	FROM Tareas T
	INNER JOIN Estatus_Cat E ON T.IdEstatus = E.IdEstatus 
	WHERE T.IdTarea = @IdTarea
END
GO
/****** Object:  StoredProcedure [dbo].[pa_s_ObtenerTareas]    Script Date: 28/03/2024 05:45:34 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[pa_s_ObtenerTareas]') AND type in (N'P', N'PC'))
BEGIN
EXEC dbo.sp_executesql @statement = N'CREATE PROCEDURE [dbo].[pa_s_ObtenerTareas] AS' 
END
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
ALTER PROCEDURE [dbo].[pa_s_ObtenerTareas]
	-- Add the parameters for the stored procedure here

AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT OFF;

    -- Insert statements for procedure here
	SELECT T.IdTarea, T.DescripcionTarea, T.IdEstatus, E.Descripcion_Estatus
	FROM Tareas T
	INNER JOIN Estatus_Cat E ON T.IdEstatus = E.IdEstatus 
END
GO
/****** Object:  StoredProcedure [dbo].[pa_s_ObtenerUsuarioPorUsername]    Script Date: 28/03/2024 05:45:34 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[pa_s_ObtenerUsuarioPorUsername]') AND type in (N'P', N'PC'))
BEGIN
EXEC dbo.sp_executesql @statement = N'CREATE PROCEDURE [dbo].[pa_s_ObtenerUsuarioPorUsername] AS' 
END
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
ALTER PROCEDURE [dbo].[pa_s_ObtenerUsuarioPorUsername] 
	-- Add the parameters for the stored procedure here
	@Username VARCHAR(MAX)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT OFF;

    -- Insert statements for procedure here
	SELECT UserName, Password, Role FROM Users WHERE UserName = @Username
END
GO
/****** Object:  StoredProcedure [dbo].[pa_u_ActualizarTarea]    Script Date: 28/03/2024 05:45:34 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[pa_u_ActualizarTarea]') AND type in (N'P', N'PC'))
BEGIN
EXEC dbo.sp_executesql @statement = N'CREATE PROCEDURE [dbo].[pa_u_ActualizarTarea] AS' 
END
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
ALTER PROCEDURE [dbo].[pa_u_ActualizarTarea]
	-- Add the parameters for the stored procedure here
	@Id INT,
	@Descripcion VARCHAR(MAX),
	@IdEstatus INT
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	UPDATE Tareas SET DescripcionTarea = @Descripcion, IdEstatus = @IdEstatus WHERE IdTarea = @Id
END
GO
