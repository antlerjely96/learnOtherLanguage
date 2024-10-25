<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class SubjectForm
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
        dgvSubject = New DataGridView()
        btnAddSubject = New Button()
        Label1 = New Label()
        txtSubjectId = New TextBox()
        txtSubjectName = New TextBox()
        Label2 = New Label()
        txtSubjectDuration = New TextBox()
        Label3 = New Label()
        txtSubejctSemester = New TextBox()
        Label4 = New Label()
        txtSubjectMajor = New TextBox()
        Label5 = New Label()
        btnSaveSubject = New Button()
        btnEditSubject = New Button()
        btnDeleteSubject = New Button()
        CType(dgvSubject, ComponentModel.ISupportInitialize).BeginInit()
        SuspendLayout()
        ' 
        ' dgvSubject
        ' 
        dgvSubject.Anchor = AnchorStyles.Top Or AnchorStyles.Left Or AnchorStyles.Right
        dgvSubject.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill
        dgvSubject.BackgroundColor = SystemColors.ControlLight
        dgvSubject.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.AutoSize
        dgvSubject.Location = New Point(247, 154)
        dgvSubject.Name = "dgvSubject"
        dgvSubject.RowHeadersWidth = 102
        dgvSubject.RowTemplate.Height = 49
        dgvSubject.SelectionMode = DataGridViewSelectionMode.FullRowSelect
        dgvSubject.Size = New Size(698, 375)
        dgvSubject.TabIndex = 0
        ' 
        ' btnAddSubject
        ' 
        btnAddSubject.Location = New Point(247, 609)
        btnAddSubject.Name = "btnAddSubject"
        btnAddSubject.Size = New Size(188, 58)
        btnAddSubject.TabIndex = 1
        btnAddSubject.Text = "Add Subject"
        btnAddSubject.UseVisualStyleBackColor = True
        ' 
        ' Label1
        ' 
        Label1.AutoSize = True
        Label1.Location = New Point(247, 728)
        Label1.Name = "Label1"
        Label1.Size = New Size(47, 41)
        Label1.TabIndex = 2
        Label1.Text = "ID"
        ' 
        ' txtSubjectId
        ' 
        txtSubjectId.Enabled = False
        txtSubjectId.Location = New Point(397, 728)
        txtSubjectId.Name = "txtSubjectId"
        txtSubjectId.Size = New Size(250, 47)
        txtSubjectId.TabIndex = 3
        ' 
        ' txtSubjectName
        ' 
        txtSubjectName.Enabled = False
        txtSubjectName.Location = New Point(397, 837)
        txtSubjectName.Name = "txtSubjectName"
        txtSubjectName.Size = New Size(250, 47)
        txtSubjectName.TabIndex = 5
        ' 
        ' Label2
        ' 
        Label2.AutoSize = True
        Label2.Location = New Point(247, 837)
        Label2.Name = "Label2"
        Label2.Size = New Size(97, 41)
        Label2.TabIndex = 4
        Label2.Text = "Name"
        ' 
        ' txtSubjectDuration
        ' 
        txtSubjectDuration.Enabled = False
        txtSubjectDuration.Location = New Point(397, 945)
        txtSubjectDuration.Name = "txtSubjectDuration"
        txtSubjectDuration.Size = New Size(250, 47)
        txtSubjectDuration.TabIndex = 7
        ' 
        ' Label3
        ' 
        Label3.AutoSize = True
        Label3.Location = New Point(247, 945)
        Label3.Name = "Label3"
        Label3.Size = New Size(133, 41)
        Label3.TabIndex = 6
        Label3.Text = "Duration"
        ' 
        ' txtSubejctSemester
        ' 
        txtSubejctSemester.Enabled = False
        txtSubejctSemester.Location = New Point(397, 1057)
        txtSubejctSemester.Name = "txtSubejctSemester"
        txtSubejctSemester.Size = New Size(250, 47)
        txtSubejctSemester.TabIndex = 9
        ' 
        ' Label4
        ' 
        Label4.AutoSize = True
        Label4.Location = New Point(247, 1057)
        Label4.Name = "Label4"
        Label4.Size = New Size(141, 41)
        Label4.TabIndex = 8
        Label4.Text = "Semester"
        ' 
        ' txtSubjectMajor
        ' 
        txtSubjectMajor.Enabled = False
        txtSubjectMajor.Location = New Point(397, 1167)
        txtSubjectMajor.Name = "txtSubjectMajor"
        txtSubjectMajor.Size = New Size(250, 47)
        txtSubjectMajor.TabIndex = 11
        ' 
        ' Label5
        ' 
        Label5.AutoSize = True
        Label5.Location = New Point(247, 1167)
        Label5.Name = "Label5"
        Label5.Size = New Size(95, 41)
        Label5.TabIndex = 10
        Label5.Text = "Major"
        ' 
        ' btnSaveSubject
        ' 
        btnSaveSubject.Enabled = False
        btnSaveSubject.Location = New Point(397, 1263)
        btnSaveSubject.Name = "btnSaveSubject"
        btnSaveSubject.Size = New Size(188, 58)
        btnSaveSubject.TabIndex = 12
        btnSaveSubject.Text = "Save"
        btnSaveSubject.UseVisualStyleBackColor = True
        ' 
        ' btnEditSubject
        ' 
        btnEditSubject.Enabled = False
        btnEditSubject.Location = New Point(515, 609)
        btnEditSubject.Name = "btnEditSubject"
        btnEditSubject.Size = New Size(188, 58)
        btnEditSubject.TabIndex = 13
        btnEditSubject.Text = "Edit Subject"
        btnEditSubject.UseVisualStyleBackColor = True
        ' 
        ' btnDeleteSubject
        ' 
        btnDeleteSubject.Enabled = False
        btnDeleteSubject.Location = New Point(777, 609)
        btnDeleteSubject.Name = "btnDeleteSubject"
        btnDeleteSubject.Size = New Size(236, 58)
        btnDeleteSubject.TabIndex = 14
        btnDeleteSubject.Text = "Delete Subject"
        btnDeleteSubject.UseVisualStyleBackColor = True
        ' 
        ' SubjectForm
        ' 
        AutoScaleDimensions = New SizeF(17F, 41F)
        AutoScaleMode = AutoScaleMode.Font
        ClientSize = New Size(1199, 1348)
        Controls.Add(btnDeleteSubject)
        Controls.Add(btnEditSubject)
        Controls.Add(btnSaveSubject)
        Controls.Add(txtSubjectMajor)
        Controls.Add(Label5)
        Controls.Add(txtSubejctSemester)
        Controls.Add(Label4)
        Controls.Add(txtSubjectDuration)
        Controls.Add(Label3)
        Controls.Add(txtSubjectName)
        Controls.Add(Label2)
        Controls.Add(txtSubjectId)
        Controls.Add(Label1)
        Controls.Add(btnAddSubject)
        Controls.Add(dgvSubject)
        Name = "SubjectForm"
        Text = "Subjects"
        CType(dgvSubject, ComponentModel.ISupportInitialize).EndInit()
        ResumeLayout(False)
        PerformLayout()
    End Sub

    Friend WithEvents dgvSubject As DataGridView
    Friend WithEvents btnAddSubject As Button
    Friend WithEvents Label1 As Label
    Friend WithEvents txtSubjectId As TextBox
    Friend WithEvents txtSubjectName As TextBox
    Friend WithEvents Label2 As Label
    Friend WithEvents txtSubjectDuration As TextBox
    Friend WithEvents Label3 As Label
    Friend WithEvents txtSubejctSemester As TextBox
    Friend WithEvents Label4 As Label
    Friend WithEvents txtSubjectMajor As TextBox
    Friend WithEvents Label5 As Label
    Friend WithEvents btnSaveSubject As Button
    Friend WithEvents btnEditSubject As Button
    Friend WithEvents btnDeleteSubject As Button
End Class
