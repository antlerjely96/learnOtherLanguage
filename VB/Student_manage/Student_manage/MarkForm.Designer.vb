<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class MarkForm
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
        cbClass = New ComboBox()
        Label1 = New Label()
        Label2 = New Label()
        cbSubject = New ComboBox()
        btnSearch = New Button()
        dgvMark = New DataGridView()
        btnAddMark = New Button()
        Label3 = New Label()
        cbStudent = New ComboBox()
        Label4 = New Label()
        cbExamType = New ComboBox()
        Label5 = New Label()
        btnSaveMark = New Button()
        txtAverageMark = New TextBox()
        btnEditMark = New Button()
        btnDeleteMark = New Button()
        CType(dgvMark, ComponentModel.ISupportInitialize).BeginInit()
        SuspendLayout()
        ' 
        ' cbClass
        ' 
        cbClass.FormattingEnabled = True
        cbClass.Location = New Point(349, 111)
        cbClass.Name = "cbClass"
        cbClass.Size = New Size(302, 49)
        cbClass.TabIndex = 0
        ' 
        ' Label1
        ' 
        Label1.AutoSize = True
        Label1.Location = New Point(156, 114)
        Label1.Name = "Label1"
        Label1.Size = New Size(172, 41)
        Label1.TabIndex = 1
        Label1.Text = "Select Class"
        ' 
        ' Label2
        ' 
        Label2.AutoSize = True
        Label2.Location = New Point(766, 114)
        Label2.Name = "Label2"
        Label2.Size = New Size(203, 41)
        Label2.TabIndex = 3
        Label2.Text = "Select Subject"
        ' 
        ' cbSubject
        ' 
        cbSubject.FormattingEnabled = True
        cbSubject.Location = New Point(993, 114)
        cbSubject.Name = "cbSubject"
        cbSubject.Size = New Size(302, 49)
        cbSubject.TabIndex = 2
        ' 
        ' btnSearch
        ' 
        btnSearch.Location = New Point(623, 206)
        btnSearch.Name = "btnSearch"
        btnSearch.Size = New Size(188, 58)
        btnSearch.TabIndex = 4
        btnSearch.Text = "Search"
        btnSearch.UseVisualStyleBackColor = True
        ' 
        ' dgvMark
        ' 
        dgvMark.Anchor = AnchorStyles.Top Or AnchorStyles.Left Or AnchorStyles.Right
        dgvMark.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill
        dgvMark.BackgroundColor = SystemColors.ControlLight
        dgvMark.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.AutoSize
        dgvMark.GridColor = SystemColors.ControlLight
        dgvMark.Location = New Point(189, 334)
        dgvMark.Name = "dgvMark"
        dgvMark.RowHeadersWidth = 102
        dgvMark.RowTemplate.Height = 49
        dgvMark.SelectionMode = DataGridViewSelectionMode.FullRowSelect
        dgvMark.Size = New Size(1046, 375)
        dgvMark.TabIndex = 5
        ' 
        ' btnAddMark
        ' 
        btnAddMark.Location = New Point(189, 771)
        btnAddMark.Name = "btnAddMark"
        btnAddMark.Size = New Size(188, 58)
        btnAddMark.TabIndex = 6
        btnAddMark.Text = "Add mark"
        btnAddMark.UseVisualStyleBackColor = True
        ' 
        ' Label3
        ' 
        Label3.AutoSize = True
        Label3.Location = New Point(189, 891)
        Label3.Name = "Label3"
        Label3.Size = New Size(206, 41)
        Label3.TabIndex = 7
        Label3.Text = "Select student"
        ' 
        ' cbStudent
        ' 
        cbStudent.Enabled = False
        cbStudent.FormattingEnabled = True
        cbStudent.Location = New Point(449, 884)
        cbStudent.Name = "cbStudent"
        cbStudent.Size = New Size(302, 49)
        cbStudent.TabIndex = 8
        ' 
        ' Label4
        ' 
        Label4.AutoSize = True
        Label4.Location = New Point(189, 988)
        Label4.Name = "Label4"
        Label4.Size = New Size(155, 41)
        Label4.TabIndex = 9
        Label4.Text = "Exam type"
        ' 
        ' cbExamType
        ' 
        cbExamType.Enabled = False
        cbExamType.FormattingEnabled = True
        cbExamType.Location = New Point(449, 988)
        cbExamType.Name = "cbExamType"
        cbExamType.Size = New Size(302, 49)
        cbExamType.TabIndex = 10
        ' 
        ' Label5
        ' 
        Label5.AutoSize = True
        Label5.Location = New Point(189, 1087)
        Label5.Name = "Label5"
        Label5.Size = New Size(199, 41)
        Label5.TabIndex = 11
        Label5.Text = "Average mark"
        ' 
        ' btnSaveMark
        ' 
        btnSaveMark.Enabled = False
        btnSaveMark.Location = New Point(189, 1178)
        btnSaveMark.Name = "btnSaveMark"
        btnSaveMark.Size = New Size(188, 58)
        btnSaveMark.TabIndex = 13
        btnSaveMark.Text = "Save"
        btnSaveMark.UseVisualStyleBackColor = True
        ' 
        ' txtAverageMark
        ' 
        txtAverageMark.Enabled = False
        txtAverageMark.Location = New Point(449, 1087)
        txtAverageMark.Name = "txtAverageMark"
        txtAverageMark.Size = New Size(250, 47)
        txtAverageMark.TabIndex = 14
        ' 
        ' btnEditMark
        ' 
        btnEditMark.Enabled = False
        btnEditMark.Location = New Point(463, 771)
        btnEditMark.Name = "btnEditMark"
        btnEditMark.Size = New Size(188, 58)
        btnEditMark.TabIndex = 15
        btnEditMark.Text = "Edit mark"
        btnEditMark.UseVisualStyleBackColor = True
        ' 
        ' btnDeleteMark
        ' 
        btnDeleteMark.Enabled = False
        btnDeleteMark.Location = New Point(752, 771)
        btnDeleteMark.Name = "btnDeleteMark"
        btnDeleteMark.Size = New Size(188, 58)
        btnDeleteMark.TabIndex = 16
        btnDeleteMark.Text = "Delete mark"
        btnDeleteMark.UseVisualStyleBackColor = True
        ' 
        ' MarkForm
        ' 
        AutoScaleDimensions = New SizeF(17F, 41F)
        AutoScaleMode = AutoScaleMode.Font
        ClientSize = New Size(1452, 1248)
        Controls.Add(btnDeleteMark)
        Controls.Add(btnEditMark)
        Controls.Add(txtAverageMark)
        Controls.Add(btnSaveMark)
        Controls.Add(Label5)
        Controls.Add(cbExamType)
        Controls.Add(Label4)
        Controls.Add(cbStudent)
        Controls.Add(Label3)
        Controls.Add(btnAddMark)
        Controls.Add(dgvMark)
        Controls.Add(btnSearch)
        Controls.Add(Label2)
        Controls.Add(cbSubject)
        Controls.Add(Label1)
        Controls.Add(cbClass)
        Name = "MarkForm"
        Text = "MarkForm"
        CType(dgvMark, ComponentModel.ISupportInitialize).EndInit()
        ResumeLayout(False)
        PerformLayout()
    End Sub

    Friend WithEvents cbClass As ComboBox
    Friend WithEvents Label1 As Label
    Friend WithEvents Label2 As Label
    Friend WithEvents cbSubject As ComboBox
    Friend WithEvents btnSearch As Button
    Friend WithEvents dgvMark As DataGridView
    Friend WithEvents btnAddMark As Button
    Friend WithEvents Label3 As Label
    Friend WithEvents cbStudent As ComboBox
    Friend WithEvents Label4 As Label
    Friend WithEvents cbExamType As ComboBox
    Friend WithEvents Label5 As Label
    Friend WithEvents btnSaveMark As Button
    Friend WithEvents txtAverageMark As TextBox
    Friend WithEvents btnEditMark As Button
    Friend WithEvents btnDeleteMark As Button
End Class
