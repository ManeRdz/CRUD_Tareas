USE [CRUDMETAPHORCE]
GO
SET IDENTITY_INSERT [dbo].[Estatus_Cat] ON 

INSERT [dbo].[Estatus_Cat] ([IdEstatus], [Descripcion_Estatus]) VALUES (1, N'Pendiente')
INSERT [dbo].[Estatus_Cat] ([IdEstatus], [Descripcion_Estatus]) VALUES (2, N'En progreso')
INSERT [dbo].[Estatus_Cat] ([IdEstatus], [Descripcion_Estatus]) VALUES (3, N'Completada')
SET IDENTITY_INSERT [dbo].[Estatus_Cat] OFF
GO
