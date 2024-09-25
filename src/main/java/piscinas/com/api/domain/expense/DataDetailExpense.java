package piscinas.com.api.domain.expense;

public record DataDetailExpense(Long id, Long customer_id, String customer_name, String description, Double value, String date) {

        public DataDetailExpense(Expense expense) {
            this(expense.getId(), expense.getCustomer().getId(), expense.getCustomer().getName(), expense.getDescription(), expense.getValue(), expense.getDate().toString());
        }
}
