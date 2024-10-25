Imports System.IO
Imports MySql.Data.MySqlClient

Public Class MarkForm
    Dim editMarkMode As Boolean = False
    Private Sub MarkForm_Load(sender As Object, e As EventArgs) Handles MyBase.Load
        Dim connectionString As String = "Server=localhost;Database=manage_student;Uid=root;Pwd=;"
        Dim sqlClass As String = "SELECT id, name FROM classes"
        Dim sqlSubject As String = "SELECT id, name FROM subjects"
        Using connection As New MySqlConnection(connectionString)
            connection.Open()
            Dim commandClass As New MySqlCommand(sqlClass, connection)
            Dim adapterClass As New MySqlDataAdapter(commandClass)
            Dim tableClass As New DataTable()
            adapterClass.Fill(tableClass)
            cbClass.DataSource = tableClass
            cbClass.DisplayMember = "name"
            cbClass.ValueMember = "id"

            Dim commandSubject As New MySqlCommand(sqlSubject, connection)
            Dim adapterSubject As New MySqlDataAdapter(commandSubject)
            Dim tableSubject As New DataTable()
            adapterSubject.Fill(tableSubject)
            cbSubject.DataSource = tableSubject
            cbSubject.DisplayMember = "name"
            cbSubject.ValueMember = "id"
        End Using
    End Sub

    Private Sub btnSearch_Click(sender As Object, e As EventArgs) Handles btnSearch.Click
        Dim studentClass As String = cbClass.SelectedValue.ToString()
        Dim studentSubject As String = cbSubject.SelectedValue.ToString()
        Dim connectionString As String = "Server=localhost;Database=manage_student;Uid=root;Pwd=;"
        Dim sql = "SELECT students.id AS 'Student id', students.full_name AS 'Student name', marks.exam_type AS 'Exam type', marks.average_mark AS 'Average mark' FROM classes INNER JOIN students ON classes.id = students.class_id LEFT JOIN marks ON students.id = marks.student_id AND marks.subject_id = @studentSubject WHERE students.class_id = @studentClass"
        Using connection As New MySqlConnection(connectionString)
            connection.Open()
            Dim command As New MySqlCommand(sql, connection)
            command.Parameters.AddWithValue("@studentSubject", studentSubject)
            command.Parameters.AddWithValue("@studentClass", studentClass)
            Dim adapter As New MySqlDataAdapter(command)
            Dim table As New DataTable()
            adapter.Fill(table)
            dgvMark.DataSource = table
        End Using
    End Sub

    Private Sub btnAddMark_Click(sender As Object, e As EventArgs) Handles btnAddMark.Click
        Dim studentClass As String = cbClass.SelectedValue.ToString()
        Dim connectionString As String = "Server=localhost;Database=manage_student;Uid=root;Pwd=;"
        Dim sql As String = "SELECT id, full_name FROM students WHERE class_id = @studentClass"
        Using connection As New MySqlConnection(connectionString)
            connection.Open()
            Dim command As New MySqlCommand(sql, connection)
            command.Parameters.AddWithValue("@studentClass", studentClass)
            Dim adapter As New MySqlDataAdapter(command)
            Dim table As New DataTable()
            adapter.Fill(table)
            cbStudent.DataSource = table
            cbStudent.DisplayMember = "full_name"
            cbStudent.ValueMember = "id"

            Dim dict As New Dictionary(Of String, String) From {
                {"Ly thuyet", "Ly thuyet"},
                {"Thuc hanh", "Thuc hanh"}
            }
            cbExamType.DataSource = New BindingSource(dict, Nothing)
            cbExamType.DisplayMember = "Value"
            cbExamType.ValueMember = "Key"
        End Using

        cbStudent.Enabled = True
        cbExamType.Enabled = True
        txtAverageMark.Enabled = True
        btnAddMark.Enabled = False
        btnSaveMark.Enabled = True
    End Sub

    Private Sub btnSaveMark_Click(sender As Object, e As EventArgs) Handles btnSaveMark.Click
        Dim studentId As String = cbStudent.SelectedValue.ToString()
        Dim examType As String = cbExamType.SelectedValue.ToString()
        Dim averageMark As String = txtAverageMark.Text
        Dim subjectId As String = cbSubject.SelectedValue.ToString()
        If editMarkMode = False Then
            Dim connectionString As String = "Server=localhost;Database=manage_student;Uid=root;Pwd=;"
            Using connection As New MySqlConnection(connectionString)
                connection.Open()
                Dim command As New MySqlCommand("INSERT INTO marks VALUES (@studentId, @subjectId, @examType, @averageMark)", connection)
                command.Parameters.AddWithValue("@studentId", studentId)
                command.Parameters.AddWithValue("@subjectId", subjectId)
                command.Parameters.AddWithValue("@examType", examType)
                command.Parameters.AddWithValue("@averageMark", averageMark)
                command.ExecuteNonQuery()
            End Using
            MarkForm_Load(sender, e)
            MessageBox.Show("Add mark successfully")
            cbStudent.ResetText()
            cbExamType.ResetText()
            txtAverageMark.ResetText()
            cbStudent.Enabled = False
            cbExamType.Enabled = False
            txtAverageMark.Enabled = False
            btnAddMark.Enabled = True
            btnSaveMark.Enabled = False
        Else
            Dim connectionString As String = "Server=localhost;Database=manage_student;Uid=root;Pwd=;"
            Using connection As New MySqlConnection(connectionString)
                connection.Open()
                Dim command As New MySqlCommand("UPDATE marks SET average_mark = @averageMark WHERE student_id = @studentId AND subject_id = @subjectId AND exam_type = @examType", connection)
                command.Parameters.AddWithValue("@studentId", studentId)
                command.Parameters.AddWithValue("@subjectId", subjectId)
                command.Parameters.AddWithValue("@examType", examType)
                command.Parameters.AddWithValue("@averageMark", averageMark)
                command.ExecuteNonQuery()
            End Using
            MarkForm_Load(sender, e)
            MessageBox.Show("Update Mark successfully")
        End If
    End Sub

    Private Sub dgvMark_CellClick(sender As Object, e As DataGridViewCellEventArgs) Handles dgvMark.CellClick
        btnAddMark.Enabled = False
        btnEditMark.Enabled = True
        btnDeleteMark.Enabled = True
        Dim studentClass As String = cbClass.SelectedValue.ToString()
        Dim connectionString As String = "Server=localhost;Database=manage_student;Uid=root;Pwd=;"
        Dim sql As String = "SELECT id, full_name FROM students WHERE class_id = @studentClass"
        Using connection As New MySqlConnection(connectionString)
            connection.Open()
            Dim command As New MySqlCommand(sql, connection)
            command.Parameters.AddWithValue("@studentClass", studentClass)
            Dim adapter As New MySqlDataAdapter(command)
            Dim table As New DataTable()
            adapter.Fill(table)
            cbStudent.DataSource = table
            cbStudent.DisplayMember = "full_name"
            cbStudent.ValueMember = "id"

            Dim dict As New Dictionary(Of String, String) From {
                {"Ly thuyet", "Ly thuyet"},
                {"Thuc hanh", "Thuc hanh"}
            }
            cbExamType.DataSource = New BindingSource(dict, Nothing)
            cbExamType.DisplayMember = "Value"
            cbExamType.ValueMember = "Key"
        End Using


        Dim row As DataGridViewRow = dgvMark.Rows(e.RowIndex)
        Dim studentId As String = row.Cells("Student id").Value.ToString()
        Dim studentName As String = row.Cells("Student name").Value.ToString()
        Dim examType As String = row.Cells("Exam type").Value.ToString()
        Dim averageMark As String = row.Cells("Average mark").Value.ToString()

        Dim indexStudentName = cbStudent.FindStringExact(studentName)
        If indexStudentName <> -1 Then
            cbStudent.SelectedIndex = indexStudentName
        End If

        Dim indexExamType = cbExamType.FindStringExact(examType)
        If indexExamType <> -1 Then
            cbExamType.SelectedIndex = indexExamType
        End If
        txtAverageMark.Text = averageMark

    End Sub

    Private Sub btnEditMark_Click(sender As Object, e As EventArgs) Handles btnEditMark.Click
        editMarkMode = True
        btnEditMark.Enabled = False
        btnSaveMark.Enabled = True
        txtAverageMark.Enabled = True
        btnDeleteMark.Enabled = False
    End Sub

    Private Sub btnDeleteMark_Click(sender As Object, e As EventArgs) Handles btnDeleteMark.Click
        Dim studentId As String = cbStudent.SelectedValue.ToString()
        Dim examType As String = cbExamType.SelectedValue.ToString()
        Dim subjectId As String = cbSubject.SelectedValue.ToString()
        Dim connectionString As String = "Server=localhost;Database=manage_student;Uid=root;Pwd=;"
        Using connection As New MySqlConnection(connectionString)
            connection.Open()
            Dim command As New MySqlCommand("DELETE FROM marks WHERE student_id = @studentId AND subject_id = @subjectId AND exam_type = @examType", connection)
            command.Parameters.AddWithValue("@studentId", studentId)
            command.Parameters.AddWithValue("@subjectId", subjectId)
            command.Parameters.AddWithValue("@examType", examType)
            command.ExecuteNonQuery()
        End Using
        MarkForm_Load(sender, e)
        MessageBox.Show("Delete Student successfully")
        cbStudent.ResetText()
        cbExamType.ResetText()
        txtAverageMark.ResetText()
        cbStudent.Enabled = False
        cbExamType.Enabled = False
        txtAverageMark.Enabled = False
        btnAddMark.Enabled = True
        btnSaveMark.Enabled = False
        btnEditMark.Enabled = False
        btnDeleteMark.Enabled = False
    End Sub
End Class