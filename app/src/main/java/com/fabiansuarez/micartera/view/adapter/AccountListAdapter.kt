package com.fabiansuarez.micartera.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.fabiansuarez.micartera.R
import com.fabiansuarez.micartera.databinding.AccountListItemBinding
import com.fabiansuarez.micartera.model.entity.Account


class AccountListAdapter(private var accounts: ArrayList<Account> = ArrayList()) :
    RecyclerView.Adapter<AccountListAdapter.AccountViewHolder>() {

    var onItemClickListener: ((Account) -> Unit)? = null
    var onItemLongClickListener: ((Account) -> Unit)? = null

    fun refresh(myAccounts: ArrayList<Account>) {
        accounts = myAccounts
        notifyDataSetChanged()
    }

    class AccountViewHolder(val binding: AccountListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            myAccount: Account,
            onItemClickListener: ((Account) -> Unit)?,
            onItemLongClickListener: ((Account) -> Unit)?
        ) {
            binding.account = myAccount
            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(myAccount)
                }
            }

            binding.root.setOnLongClickListener {
                onItemLongClickListener?.let {
                    it(myAccount)
                }
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        val binding: AccountListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.account_list_item,
            parent,
            false
        )
        return AccountViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        holder.bind(accounts[position], onItemClickListener, onItemLongClickListener)
    }

    override fun getItemCount(): Int = accounts.size
}
