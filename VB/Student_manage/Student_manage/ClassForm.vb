Imports MySql.Data.MySqlClient

Public Class ClassForm

    Private Sub ClassForm_Load(sender As Object, e As EventArgs) Handles MyBase.Load
        Dim connectionString As String = "Server=localhost;Database=manage_student;Uid=root;Pwd=;"
        Using connection As New MySqlConnection(connectionString)
            connection.Open()
            Dim command As New MySqlCommand("SELECT * FROM classes", connection)
            Dim adapter As New MySqlDataAdapter(command)
            Dim table As New DataTable()
            adapter.Fill(table)
            dgvClasses.DataSource = table
        End Using
    End Sub

    Private Sub btnAddClass_Click(sender As Object, e As EventArgs) Handles btnAddClass.Click
        txtCodeClass.Enabled = True
        txtNameClass.Enabled = True
        txtMajorClass.Enabled = True
        txtSchoolYearClass.Enabled = True
        btnSave.Enabled = True
        btnAddClass.Enabled = False
    End Sub

    Private Sub btnSave_Click(sender As Object, e As EventArgs) Handles btnSave.Click
        Dim classId As String = txtIdClass.Text
        Dim classCode As String = txtCodeClass.Text
        Dim className As String = txtNameClass.Text
        Dim classMajor As String = txtMajorClass.Text
        Dim classSchoolYear As String = txtSchoolYearClass.Text
        If String.IsNullOrEmpty(classId) Then
            Dim connectionString As String = "Server=localhost;Database=manage_student;Uid=root;Pwd=;"
            Using connection As New MySqlConnection(connectionString)
                connection.Open()
                Dim command As New MySqlCommand("INSERT INTO classes(code, name, major, school_year) VALUES (@classCode, @className, @classMajor, @classSchoolYear )", connection)
                command.Parameters.AddWithValue("@classCode", classCode)
                command.Parameters.AddWithValue("@className", className)
                command.Parameters.AddWithValue("@classMajor", classMajor)
                command.Parameters.AddWithValue("@classSchoolYear", classSchoolYear)
                command.ExecuteNonQuery()
            End Using
            ClassForm_Load(sender, e)
            MessageBox.Show("Add class successfully")
        Else
            Dim connectionString As String = "Server=localhost;Database=manage_student;Uid=root;Pwd=;"
            Using connection As New MySqlConnection(connectionString)
                connection.Open()
                Dim command As New MySqlCommand("UPDATE classes SET code = @classCode, name = @className, major = @classMajor, school_year = @classSchoolYear WHERE id = @classId", connection)
                command.Parameters.AddWithValue("@classId", classId)
                command.Parameters.AddWithValue("@classCode", classCode)
                command.Parameters.AddWithValue("@className", className)
                command.Parameters.AddWithValue("@classMajor", classMajor)
                command.Parameters.AddWithValue("@classSchoolYear", classSchoolYear)
                command.ExecuteNonQuery()
            End Using
            ClassForm_Load(sender, e)
            MessageBox.Show("Update class successfully")
        End If
        txtIdClass.Text = ""
        txtCodeClass.Text = ""
        txtNameClass.Text = ""
        txtMajorClass.Text = ""
        txtSchoolYearClass.Text = ""
        txtCodeClass.Enabled = False
        txtNameClass.Enabled = False
        txtMajorClass.Enabled = False
        txtSchoolYearClass.Enabled = False
        btnSave.Enabled = False
        btnEditClass.Enabled = False
        btnDeleteClass.Enabled = False
        btnAddClass.Enabled = True
    End Sub

    Private Sub dgvClasses_CellClick(sender As Object, e As DataGridViewCellEventArgs) Handles dgvClasses.CellClick
        Dim row As DataGridViewRow = dgvClasses.Rows(e.RowIndex)
        Dim classId As String = row.Cells("id").Value.ToString()
        Dim classCode As String = row.Cells("code").Value.ToString()
        Dim className As String = row.Cells("name").Value.ToString()
        Dim classMajor As String = row.Cells("major").Value.ToString()
        Dim classSchoolYear As String = row.Cells("school_year").Value.ToString()
        txtIdClass.Text = classId
        txtCodeClass.Text = classCode
        txtNameClass.Text = className
        txtMajorClass.Text = classMajor
        txtSchoolYearClass.Text = classSchoolYear
        btnAddClass.Enabled = False
        btnEditClass.Enabled = True
        btnDeleteClass.Enabled = True
    End Sub

    Private Sub btnEditClass_Click(sender As Object, e As EventArgs) Handles btnEditClass.Click
        btnEditClass.Enabled = True
        txtCodeClass.Enabled = True
        txtNameClass.Enabled = True
        txtMajorClass.Enabled = True
        txtSchoolYearClass.Enabled = True
        btnSave.Enabled = True
        btnDeleteClass.Enabled = False
    End Sub

    Private Sub btnDeleteClass_Click(sender As Object, e As EventArgs) Handles btnDeleteClass.Click
        Dim classId As String = txtIdClass.Text
        Dim connectionString As String = "Server=localhost;Database=manage_student;Uid=root;Pwd=;"
        Using connection As New MySqlConnection(connectionString)
            connection.Open()
            Dim command As New MySqlCommand("DELETE FROM classes WHERE id = @classId", connection)
            command.Parameters.AddWithValue("@classId", classId)
            command.ExecuteNonQuery()
        End Using
        ClassForm_Load(sender, e)
        MessageBox.Show("Delete class successfully")
        btnDeleteClass.Enabled = False
        btnEditClass.Enabled = False
        btnAddClass.Enabled = True
        txtIdClass.Text = ""
        txtCodeClass.Text = ""
        txtNameClass.Text = ""
        txtMajorClass.Text = ""
        txtSchoolYearClass.Text = ""
        txtCodeClass.Enabled = False
        txtNameClass.Enabled = False
        txtMajorClass.Enabled = False
        txtSchoolYearClass.Enabled = False
        btnSave.Enabled = False
    End Sub

    Private Sub btnMoveToSubject_Click(sender As Object, e As EventArgs) Handles btnMoveToSubject.Click
        Dim formSubject As New SubjectForm()
        formSubject.Show()
    End Sub

    Private Sub btnMoveToStudent_Click(sender As Object, e As EventArgs) Handles btnMoveToStudent.Click
        Dim formStudent As New StudentForm()
        formStudent.Show()
    End Sub

    Private Sub btnMoveToMark_Click(sender As Object, e As EventArgs) Handles btnMoveToMark.Click
        Dim formMark As New MarkForm()
        formMark.Show()
    End Sub
End Class
