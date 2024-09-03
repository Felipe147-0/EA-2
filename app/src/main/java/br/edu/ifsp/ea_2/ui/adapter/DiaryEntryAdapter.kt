package br.edu.ifsp.ea_2.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.ea_2.R
import br.edu.ifsp.ea_2.data.model.DiaryEntry
import br.edu.ifsp.ea_2.databinding.ItemDiaryentryBinding
import br.edu.ifsp.ea_2.ui.listeners.DiaryEntryItemClickListener

class DiaryEntryAdapter (private val listener: DiaryEntryItemClickListener) :
        RecyclerView.Adapter<DiaryEntryAdapter.ViewHolder>() {

        private var dataset: List<DiaryEntry> = emptyList()
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_diaryentry, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val diaryEntry = dataset[position]

            holder.binding.textviewTitleField.text = diaryEntry.title
            holder.binding.textviewNoteField.text = diaryEntry.note
            holder.binding.textviewLocationField.text = diaryEntry.location
            holder.binding.textviewDateField.text = diaryEntry.date
            holder.binding.textviewTimeField.text = diaryEntry.time
            holder.binding.buttonDelete.setOnClickListener {
                listener.clickDelete(diaryEntry.id)
            }

            holder.itemView.setOnLongClickListener {
                listener.clickOpen(diaryEntry.id)
                true
            }
        }

        override fun getItemCount(): Int {
            return dataset.size
        }

        fun submitDataset(data: List<DiaryEntry>) {
            dataset = data
            this.notifyDataSetChanged()
        }

        fun getDatasetItem(position: Int): DiaryEntry {
            return dataset[position]
        }

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val binding: ItemDiaryentryBinding = ItemDiaryentryBinding.bind(view)
        }
    }
