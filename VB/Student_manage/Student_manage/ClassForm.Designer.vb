<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()>
Partial Class ClassForm
    Inherits System.Windows.Forms.Form

    'Form overrides dispose to clean up the component list.
    <System.Diagnostics.DebuggerNonUserCode()>
    Protected Overrides Sub Dispose(ByVal disposing As Boolean)
        Try
            If disposing AndAlso components IsNot Nothing Then
                components.Dispose()
            End If
        Finally
            MyBase.Dispose(disposing)
        End Try
    End Sub

    'Required by the Windows Form Designer
    Private components As System.ComponentModel.IContainer

    'NOTE: The following procedure is required by the Windows Form Designer
    'It can be modified using the Windows Form Designer.  
    'Do not modify it using the code editor.
    <System.Diagnostics.DebuggerStepThrough()>
    Private Sub InitializeComponent()
        dgvClasses = New DataGridView()
        btnAddClass = New Button()
        Label1 = New Label()
        txtIdClass = New TextBox()
        Label2 = New Label()
        txtNameClass = New TextBox()
        txtCodeClass = New TextBox()
        Label3 = New Label()
        txtMajorClass = New TextBox()
        Label4 = New Label()
        txtSchoolYearClass = New TextBox()
        Label5 = New Label()
        btnSave = New Button()
        btnEditClass = New Button()
        btnDeleteClass = New Button()
        btnMoveToSubject = New Button()
        btnMoveToStudent = New Button()
        btnMoveToMark = New Button()
        CType(dgvClasses, ComponentModel.ISupportInitialize).BeginInit()
        SuspendLayout()
        ' 
        ' dgvClasses
        ' 
        dgvClasses.AllowUserToAddRows = False
        dgvClasses.AllowUserToDeleteRows = False
        dgvClasses.Anchor = AnchorStyles.Top Or AnchorStyles.Left Or AnchorStyles.Right
        dgvClasses.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill
        dgvClasses.BackgroundColor = SystemColors.ControlLight
        dgvClasses.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.AutoSize
        dgvClasses.Location = New Point(158, 330)
        dgvClasses.Name = "dgvClasses"
        dgvClasses.ReadOnly = True
        dgvClasses.RowHeadersWidthSizeMode = DataGridViewRowHeadersWidthSizeMode.AutoSizeToAllHeaders
        dgvClasses.RowTemplate.Height = 49
        dgvClasses.SelectionMode = DataGridViewSelectionMode.FullRowSelect
        dgvClasses.Size = New Size(1104, 804)
        dgvClasses.TabIndex = 0
        ' 
        ' btnAddClass
        ' 
        btnAddClass.Location = New Point(158, 1192)
        btnAddClass.Name = "btnAddClass"
        btnAddClass.Size = New Size(188, 58)
        btnAddClass.TabIndex = 1
        btnAddClass.Text = "Add Class"
        btnAddClass.UseVisualStyleBackColor = True
        ' 
        ' Label1
        ' 
        Label1.AutoSize = True
        Label1.Location = New Point(158, 1311)
        Label1.Name = "Label1"
        Label1.Size = New Size(47, 41)
        Label1.TabIndex = 2
        Label1.Text = "ID"
        ' 
        ' txtIdClass
        ' 
        txtIdClass.Enabled = False
        txtIdClass.Location = New Point(363, 1311)
        txtIdClass.Name = "txtIdClass"
        txtIdClass.Size = New Size(250, 47)
        txtIdClass.TabIndex = 3
        ' 
        ' Label2
        ' 
        Label2.AutoSize = True
        Label2.Location = New Point(158, 1538)
        Label2.Name = "Label2"
        Label2.Size = New Size(97, 41)
        Label2.TabIndex = 4
        Label2.Text = "Name"
        ' 
        ' txtNameClass
        ' 
        txtNameClass.Enabled = False
        txtNameClass.Location = New Point(363, 1538)
        txtNameClass.Name = "txtNameClass"
        txtNameClass.Size = New Size(250, 47)
        txtNameClass.TabIndex = 5
        ' 
        ' txtCodeClass
        ' 
        txtCodeClass.Enabled = False
        txtCodeClass.Location = New Point(363, 1428)
        txtCodeClass.Name = "txtCodeClass"
        txtCodeClass.Size = New Size(250, 47)
        txtCodeClass.TabIndex = 7
        ' 
        ' Label3
        ' 
        Label3.AutoSize = True
        Label3.Location = New Point(158, 1428)
        Label3.Name = "Label3"
        Label3.Size = New Size(89, 41)
        Label3.TabIndex = 6
        Label3.Text = "Code"
        ' 
        ' txtMajorClass
        ' 
        txtMajorClass.Enabled = False
        txtMajorClass.Location = New Point(363, 1648)
        txtMajorClass.Name = "txtMajorClass"
        txtMajorClass.Size = New Size(250, 47)
        txtMajorClass.TabIndex = 9
        ' 
        ' Label4
        ' 
        Label4.AutoSize = True
        Label4.Location = New Point(158, 1648)
        Label4.Name = "Label4"
        Label4.Size = New Size(95, 41)
        Label4.TabIndex = 8
        Label4.Text = "Major"
        ' 
        ' txtSchoolYearClass
        ' 
        txtSchoolYearClass.Enabled = False
        txtSchoolYearClass.Location = New Point(363, 1761)
        txtSchoolYearClass.Name = "txtSchoolYearClass"
        txtSchoolYearClass.Size = New Size(250, 47)
        txtSchoolYearClass.TabIndex = 11
        ' 
        ' Label5
        ' 
        Label5.AutoSize = True
        Label5.Location = New Point(161, 1761)
        Label5.Name = "Label5"
        Label5.Size = New Size(172, 41)
        Label5.TabIndex = 10
        Label5.Text = "School year"
        ' 
        ' btnSave
        ' 
        btnSave.Enabled = False
        btnSave.Location = New Point(363, 1868)
        btnSave.Name = "btnSave"
        btnSave.Size = New Size(188, 58)
        btnSave.TabIndex = 12
        btnSave.Text = "Save"
        btnSave.UseVisualStyleBackColor = False
        ' 
        ' btnEditClass
        ' 
        btnEditClass.Enabled = False
        btnEditClass.Location = New Point(415, 1192)
        btnEditClass.Name = "btnEditClass"
        btnEditClass.Size = New Size(188, 58)
        btnEditClass.TabIndex = 13
        btnEditClass.Text = "Edit Class"
        btnEditClass.UseVisualStyleBackColor = True
        ' 
        ' btnDeleteClass
        ' 
        btnDeleteClass.Enabled = False
        btnDeleteClass.Location = New Point(679, 1192)
        btnDeleteClass.Name = "btnDeleteClass"
        btnDeleteClass.Size = New Size(188, 58)
        btnDeleteClass.TabIndex = 14
        btnDeleteClass.Text = "Delete Class"
        btnDeleteClass.UseVisualStyleBackColor = True
        ' 
        ' btnMoveToSubject
        ' 
        btnMoveToSubject.Location = New Point(158, 146)
        btnMoveToSubject.Name = "btnMoveToSubject"
        btnMoveToSubject.Size = New Size(286, 58)
        btnMoveToSubject.TabIndex = 15
        btnMoveToSubject.Text = "Subject Manage"
        btnMoveToSubject.UseVisualStyleBackColor = True
        ' 
        ' btnMoveToStudent
        ' 
        btnMoveToStudent.Location = New Point(538, 146)
        btnMoveToStudent.Name = "btnMoveToStudent"
        btnMoveToStudent.Size = New Size(286, 58)
        btnMoveToStudent.TabIndex = 16
        btnMoveToStudent.Text = "Student Manage"
        btnMoveToStudent.UseVisualStyleBackColor = True
        ' 
        ' btnMoveToMark
        ' 
        btnMoveToMark.Location = New Point(921, 146)
        btnMoveToMark.Name = "btnMoveToMark"
        btnMoveToMark.Size = New Size(286, 58)
        btnMoveToMark.TabIndex = 17
        btnMoveToMark.Text = "Mark Manage"
        btnMoveToMark.UseVisualStyleBackColor = True
        ' 
        ' ClassForm
        ' 
        AutoScaleDimensions = New SizeF(17.0F, 41.0F)
        AutoScaleMode = AutoScaleMode.Font
        ClientSize = New Size(1374, 1947)
        Controls.Add(btnMoveToMark)
        Controls.Add(btnMoveToStudent)
        Controls.Add(btnMoveToSubject)
        Controls.Add(btnDeleteClass)
        Controls.Add(btnEditClass)
        Controls.Add(btnSave)
        Controls.Add(txtSchoolYearClass)
        Controls.Add(Label5)
        Controls.Add(txtMajorClass)
        Controls.Add(Label4)
        Controls.Add(txtCodeClass)
        Controls.Add(Label3)
        Controls.Add(txtNameClass)
        Controls.Add(Label2)
        Controls.Add(txtIdClass)
        Controls.Add(Label1)
        Controls.Add(btnAddClass)
        Controls.Add(dgvClasses)
        Name = "ClassForm"
        Text = "Classes"
        CType(dgvClasses, ComponentModel.ISupportInitialize).EndInit()
        ResumeLayout(False)
        PerformLayout()
    End Sub

    Friend WithEvents dgvClasses As DataGridView
    Friend WithEvents btnAddClass As Button
    Friend WithEvents Label1 As Label
    Friend WithEvents txtIdClass As TextBox
    Friend WithEvents Label2 As Label
    Friend WithEvents txtNameClass As TextBox
    Friend WithEvents txtCodeClass As TextBox
    Friend WithEvents Label3 As Label
    Friend WithEvents txtMajorClass As TextBox
    Friend WithEvents Label4 As Label
    Friend WithEvents txtSchoolYearClass As TextBox
    Friend WithEvents Label5 As Label
    Friend WithEvents btnSave As Button
    Friend WithEvents btnEditClass As Button
    Friend WithEvents btnDeleteClass As Button
    Friend WithEvents btnMoveToSubject As Button
    Friend WithEvents btnMoveToStudent As Button
    Friend WithEvents btnMoveToMark As Button
End Class
