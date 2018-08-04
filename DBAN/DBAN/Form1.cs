using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace DBAN
{
    public partial class DBAN : Form
    {
        private List<Drive> drives = new List<Drive> { };

        public DBAN()
        {
            InitializeComponent();
            GetDrive();
        }

        private void GetDrive()
        {
            foreach (System.IO.DriveInfo driveInfo in System.IO.DriveInfo.GetDrives())
            {
                if (!driveInfo.DriveType.ToString().Equals("Fixed"))
                {
                    String type = driveInfo.DriveType.ToString();
                    if (type.Equals(""))
                    {
                        type = "";
                    }
                    Drive tempDrive = new Drive(driveInfo.Name, driveInfo.VolumeLabel, type, driveInfo.DriveFormat, driveInfo.VolumeLabel);
                    drives.Add(tempDrive);
                    drivesCB.Items.Add(tempDrive.letter + " - " + tempDrive.name);
                }
            }
        }

        private void drivesCB_SelectedIndexChanged(object sender, EventArgs e)
        {
            nameTB.Text = drives[drivesCB.SelectedIndex].name;
            typeTB.Text = drives[drivesCB.SelectedIndex].type;
            formatTB.Text = drives[drivesCB.SelectedIndex].format;
            sizeTB.Text = drives[drivesCB.SelectedIndex].size;
        }

        private void dbanBtn_Click(object sender, EventArgs e)
        {
            var result = MessageBox.Show("Are you sure you want to DBAN " + drives[drivesCB.SelectedIndex].name + " ?", "NTID DBAN", MessageBoxButtons.YesNo, MessageBoxIcon.Warning);
            if(result == DialogResult.Yes)
            {
                MessageBox.Show("Wipe complete!", "NTID DBAN", MessageBoxButtons.OK ,MessageBoxIcon.Information);
            }
            else
            {
                MessageBox.Show("Wipe stopped", "NTID DBAN", MessageBoxButtons.OK, MessageBoxIcon.Information);
            }
        }
    }

    public class Drive
    {
        public string letter { get; set; }
        public string name { get; set; }
        public string type { get; set; }
        public string format { get; set; }
        public string size { get; set; }

        public Drive(String letterIn, String nameIn, String typeIn, String formatIn, String sizeIn)
        {
            letter = letterIn;
            name = nameIn;
            type = typeIn;
            format = formatIn;
            size = sizeIn;
        }
    }
}
