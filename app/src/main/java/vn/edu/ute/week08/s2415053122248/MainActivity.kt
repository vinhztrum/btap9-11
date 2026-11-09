package vn.edu.ute.week08.s2415053122248

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import vn.edu.ute.week08.s2415053122248.databinding.ActivityMainBinding
import java.util.Locale
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnKiemTra.setOnClickListener {
            thucHienKiemTra()
        }
        binding.btnXoa.setOnClickListener {
            thucHienXoa()
        }

    }

        private fun thucHienKiemTra() {
            val tenHocPhan = binding.edtTenHocPhan.text.toString()
            val QT = binding.edtDiemQT.text.toString()
            val CK = binding.edtDiemCK.text.toString()
            binding.edtTenHocPhan.error = null
            binding.edtDiemQT.error = null
            binding.edtDiemCK.error = null
            binding.tvKetQua.text = ""
            if (kiemTraDauVao(tenHocPhan, QT, CK)) {
                val diemQT = QT.toDouble()
                val diemCK = CK.toDouble()
                val tenHP = tenHocPhan.trim()
                val t = tinhDiem(diemQT, diemCK)
                val KetLuan = phanLoaiKetQua(t, diemCK)
                val tFormatted = String.format(Locale.getDefault(), "%.2f", t)
                val ketQuaCuoiCung = "Tên học phần:$tenHP\n"
                "Điểm QT: $diemQT\n" +
                        "Điểm CK: $diemCK\n" +
                        "Điểm tổng kết (T):$tFormatted\n" +
                        "Kết luận: $KetLuan"
                binding.tvKetQua.text = ketQuaCuoiCung
            }

        }

        private fun thucHienXoa() {
            binding.edtTenHocPhan.text.clear()
            binding.edtDiemQT.text.clear()
            binding.edtDiemCK.text.clear()
            binding.tvKetQua.text = ""
            binding.edtTenHocPhan.error = null
            binding.edtDiemQT.error = null
            binding.edtDiemCK.error = null
            binding.edtTenHocPhan.requestFocus()
        }

        private fun kiemTraDauVao(tenHocPhan: String, diemQT: String, diemCK: String): Boolean {
            var hopLe = true
            val tenHP = tenHocPhan.trim()
            if (tenHP.isEmpty() || tenHP.length > 50) {
                binding.edtTenHocPhan.error = "Tên học phần không hợp lệ"
                hopLe = false
            }
            try {
                val qt = diemQT.toDouble()
                if (qt < 0.0 || qt > 10.0) {
                    binding.edtDiemQT.error = "Điểm QT không hợp lệ"
                    hopLe = false
                }
            } catch (e: NumberFormatException) {
                binding.edtDiemQT.error = "Điểm QT từ 0.0-10.0"
                hopLe = false
            }
            try {
                val ck = diemCK.toDouble()
                if (ck < 0.0 || ck > 10.0) {
                    binding.edtDiemCK.error = "Điểm CK không hợp lệ"
                    hopLe = false
                }
            } catch (e: NumberFormatException) {
                binding.edtDiemCK.error = "Điểm CK từ 0.0-10.0"
                hopLe = false
            }
            return hopLe

        }

        private fun tinhDiem(diemQT: Double, diemCK: Double): Double {
            return (0.4 * diemQT + 0.6 * diemCK)

        }

        private fun phanLoaiKetQua(diemTongKet: Double, diemCK: Double): String {
            return if (diemTongKet >= 5.0 || diemCK >= 5.0) {
                "Đạt"
            } else {
                "Không đạt"
            }
        }
    }
