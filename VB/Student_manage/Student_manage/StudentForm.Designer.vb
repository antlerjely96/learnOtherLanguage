<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class StudentForm
    Inherits System.Windows.Forms.Form

    'Form overrides dispose to clean up the component list.
    <System.Diagnostics.DebuggerNonUserCode()> _
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
    <System.Diagnostics.DebuggerStepThrough()> _
    Private Sub InitializeComponent()
        dgvStudent = New DataGridView()
        btnAddStudent = New Button()
        btnEditStudent = New Button()
        btnDeleteStudent = New Button()
        Label1 = New Label()
        txtStudentId = New TextBox()
        txtStudentName = New TextBox()
        Label2 = New Label()
        Label3 = New Label()
        txtStudentEmail = New TextBox()
        Label4 = New Label()
        txtStudentAddress = New TextBox()
        Label5 = New Label()
        txtStudentPhoneNumber = New TextBox()
        Label6 = New Label()
        btnSaveStudent = New Button()
        dtpDateOfBirth = New DateTimePicker()
        Label7 = New Label()
        cbStudentClass = New ComboBox()
        CType(dgvStudent, ComponentModel.ISupportInitialize).BeginInit()
        SuspendLayout()
        ' 
        ' dgvStudent
        ' 
        dgvStudent.Anchor = AnchorStyles.Top Or AnchorStyles.Left Or AnchorStyles.Right
        dgvStudent.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill
        dgvStudent.BackgroundColor = SystemColors.ControlLight
        dgvStudent.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.AutoSize
        dgvStudent.GridColor = SystemColors.ControlLight
        dgvStudent.Location = New Point(227, 164)
        dgvStudent.Name = "dgvStudent"
        dgvStudent.RowHeadersWidth = 102
        dgvStudent.RowTemplate.Height = 49
        dgvStudent.SelectionMode = DataGridViewSelectionMode.FullRowSelect
        dgvStudent.Size = New Size(1205, 375)
        dgvStudent.TabIndex = 0
        ' 
        ' btnAddStudent
        ' 
        btnAddStudent.Location = New Point(225, 620)
        btnAddStudent.Name = "btnAddStudent"
        btnAddStudent.Size = New Size(218, 58)
        btnAddStudent.TabIndex = 1
        btnAddStudent.Text = "Add Student"
        btnAddStudent.UseVisualStyleBackColor = True
        ' 
        ' btnEditStudent
        ' 
        btnEditStudent.Enabled = False
        btnEditStudent.Location = New Point(549, 620)
        btnEditStudent.Name = "btnEditStudent"
        btnEditStudent.Size = New Size(218, 58)
        btnEditStudent.TabIndex = 2
        btnEditStudent.Text = "Edit Student"
        btnEditStudent.UseVisualStyleBackColor = True
        ' 
        ' btnDeleteStudent
        ' 
        btnDeleteStudent.Enabled = False
        btnDeleteStudent.Location = New Point(897, 620)
        btnDeleteStudent.Name = "btnDeleteStudent"
        btnDeleteStudent.Size = New Size(269, 58)
        btnDeleteStudent.TabIndex = 3
        btnDeleteStudent.Text = "Delete Student"
        btnDeleteStudent.UseVisualStyleBackColor = True
        ' 
        ' Label1
        ' 
        Label1.AutoSize = True
        Label1.Location = New Point(225, 751)
        Label1.Name = "Label1"
        Label1.Size = New Size(47, 41)
        Label1.TabIndex = 4
        Label1.Text = "ID"
        ' 
        ' txtStudentId
        ' 
        txtStudentId.Enabled = False
        txtStudentId.Location = New Point(384, 751)
        txtStudentId.Name = "txtStudentId"
        txtStudentId.Size = New Size(250, 47)
        txtStudentId.TabIndex = 5
        ' 
        ' txtStudentName
        ' 
        txtStudentName.Enabled = False
        txtStudentName.Location = New Point(1001, 751)
        txtStudentName.Name = "txtStudentName"
        txtStudentName.Size = New Size(250, 47)
        txtStudentName.TabIndex = 7
        ' 
        ' Label2
        ' 
        Label2.AutoSize = True
        Label2.Location = New Point(760, 751)
        Label2.Name = "Label2"
        Label2.Size = New Size(203, 41)
        Label2.TabIndex = 6
        Label2.Text = "Student name"
        ' 
        ' Label3
        ' 
        Label3.AutoSize = True
        Label3.Location = New Point(760, 844)
        Label3.Name = "Label3"
        Label3.Size = New Size(127, 41)
        Label3.TabIndex = 8
        Label3.Text = "Birthday"
        ' 
        ' txtStudentEmail
        ' 
        txtStudentEmail.Enabled = False
        txtStudentEmail.Location = New Point(384, 838)
        txtStudentEmail.Name = "txtStudentEmail"
        txtStudentEmail.Size = New Size(250, 47)
        txtStudentEmail.TabIndex = 11
        ' 
        ' Label4
        ' 
        Label4.AutoSize = True
        Label4.Location = New Point(225, 838)
        Label4.Name = "Label4"
        Label4.Size = New Size(88, 41)
        Label4.TabIndex = 10
        Label4.Text = "Email"
        ' 
        ' txtStudentAddress
        ' 
        txtStudentAddress.Enabled = False
        txtStudentAddress.Location = New Point(384, 931)
        txtStudentAddress.Name = "txtStudentAddress"
        txtStudentAddress.Size = New Size(250, 47)
        txtStudentAddress.TabIndex = 13
        ' 
        ' Label5
        ' 
        Label5.AutoSize = True
        Label5.Location = New Point(225, 931)
        Label5.Name = "Label5"
        Label5.Size = New Size(125, 41)
        Label5.TabIndex = 12
        Label5.Text = "Address"
        ' 
        ' txtStudentPhoneNumber
        ' 
        txtStudentPhoneNumber.Enabled = False
        txtStudentPhoneNumber.Location = New Point(1001, 931)
        txtStudentPhoneNumber.Name = "txtStudentPhoneNumber"
        txtStudentPhoneNumber.Size = New Size(250, 47)
        txtStudentPhoneNumber.TabIndex = 15
        ' 
        ' Label6
        ' 
        Label6.AutoSize = True
        Label6.Location = New Point(760, 931)
        Label6.Name = "Label6"
        Label6.Size = New Size(215, 41)
        Label6.TabIndex = 14
        Label6.Text = "Phone number"
        ' 
        ' btnSaveStudent
        ' 
        btnSaveStudent.Enabled = False
        btnSaveStudent.Location = New Point(669, 1138)
        btnSaveStudent.Name = "btnSaveStudent"
        btnSaveStudent.Size = New Size(218, 58)
        btnSaveStudent.TabIndex = 16
        btnSaveStudent.Text = "Save"
        btnSaveStudent.UseVisualStyleBackColor = True
        ' 
        ' dtpDateOfBirth
        ' 
        dtpDateOfBirth.Enabled = False
        dtpDateOfBirth.Location = New Point(1001, 844)
        dtpDateOfBirth.Name = "dtpDateOfBirth"
        dtpDateOfBirth.Size = New Size(487, 47)
        dtpDateOfBirth.TabIndex = 17
        ' 
        ' Label7
        ' 
        Label7.AutoSize = True
        Label7.Location = New Point(225, 1036)
        Label7.Name = "Label7"
        Label7.Size = New Size(85, 41)
        Label7.TabIndex = 18
        Label7.Text = "Class"
        ' 
        ' cbStudentClass
        ' 
        cbStudentClass.Enabled = False
        cbStudentClass.FormattingEnabled = True
        cbStudentClass.Location = New Point(384, 1036)
        cbStudentClass.Name = "cbStudentClass"
        cbStudentClass.Size = New Size(302, 49)
        cbStudentClass.TabIndex = 19
        ' 
        ' StudentForm
        ' 
        AutoScaleDimensions = New SizeF(17F, 41F)
        AutoScaleMode = AutoScaleMode.Font
        ClientSize = New Size(1628, 1300)
        Controls.Add(cbStudentClass)
        Controls.Add(Label7)
        Controls.Add(dtpDateOfBirth)
        Controls.Add(btnSaveStudent)
        Controls.Add(txtStudentPhoneNumber)
        Controls.Add(Label6)
        Controls.Add(txtStudentAddress)
        Controls.Add(Label5)
        Controls.Add(txtStudentEmail)
        Controls.Add(Label4)
        Controls.Add(Label3)
        Controls.Add(txtStudentName)
        Controls.Add(Label2)
        Controls.Add(txtStudentId)
        Controls.Add(Label1)
        Controls.Add(btnDeleteStudent)
        Controls.Add(btnEditStudent)
        Controls.Add(btnAddStudent)
        Controls.Add(dgvStudent)
        Name = "StudentForm"
        Text = "StudentForm"
        CType(dgvStudent, ComponentModel.ISupportInitialize).EndInit()
        ResumeLayout(False)
        PerformLayout()
    End Sub

    Friend WithEvents dgvStudent As DataGridView
    Friend WithEvents btnAddStudent As Button
    Friend WithEvents btnEditStudent As Button
    Friend WithEvents btnDeleteStudent As Button
    Friend WithEvents Label1 As Label
    Friend WithEvents txtStudentId As TextBox
    Friend WithEvents txtStudentName As TextBox
    Friend WithEvents Label2 As Label
    Friend WithEvents Label3 As Label
    Friend WithEvents txtStudentEmail As TextBox
    Friend WithEvents Label4 As Label
    Friend WithEvents txtStudentAddress As TextBox
    Friend WithEvents Label5 As Label
    Friend WithEvents txtStudentPhoneNumber As TextBox
    Friend WithEvents Label6 As Label
    Friend WithEvents btnSaveStudent As Button
    Friend WithEvents dtpDateOfBirth As DateTimePicker
    Friend WithEvents Label7 As Label
    Friend WithEvents cbStudentClass As ComboBox
End Class
