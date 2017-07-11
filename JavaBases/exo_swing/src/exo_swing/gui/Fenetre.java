package exo_swing.gui;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.security.auth.callback.ChoiceCallback;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import exo_swing.metier.Contact;

public class Fenetre extends JFrame implements ListSelectionListener, ActionListener {
	
	public static final String TRI_ID_COMMAND = "id_tri";
	public static final String TRI_NOM_COMMAND = "nom_tri";
	public static final String TRI_PRENOM_COMMAND = "prenom_tri";
	public static final String TRI_EMAIL_COMMAND = "email_tri";
	public static final String TRI_AGE_COMMAND = "age_tri";
	public static  boolean IS_GOLD_FILTER_ACTIVE = false;
	public static final String GOLD = "gold";
	public static final String AGE_FILTER = "filtre_age";
	
	private Predicate<Contact> currentFilter;
	private Comparator<Contact> currentSort;
	private Predicate<Contact> isGold;
	private Predicate<Contact> ageFilter;
	private JList<String> listeReferent;
	
	private JPanel panelTri;
	private JButton triName;
	private JButton triFirstname;
	private JButton triDefault;
	private JButton triEmail;
	private JButton triAge;
	private JCheckBox checkGold;
	private JTextField ageTextFilter;
	private JPanel panel;
	
	private JList<Contact> contactVisible;
	private DefaultListModel<Contact> contactVsisibleData;
	private ArrayList<Contact> contactFullData;
	
	public Fenetre() throws HeadlessException {
		super("Repertoire contact");
		
		setSize(800, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		contactFullData = new ArrayList<>();
		contactFullData.add(new Contact(1, "otman", "badman", "o@o.fr", 0, 29, true, "courtaon"));
		contactFullData.add(new Contact(2, "maurille", "toto", "t@t.fr", 0, 25, false, "courtalon"));
		contactFullData.add(new Contact(4, "toto", "francois", "f@f.fr", 1, 22, false, "nadia"));
		contactFullData.add(new Contact(3, "nadia", "accueil", "n@n.fr", 1, 28, true, "the boosss"));

		
		// LISTE DES REFERENT A GAUCHE
		java.util.List<String> referents = contactFullData.stream()
					.map(c->c.getReferent())
					.distinct()
					.collect(Collectors.toList());
		referents.add("all");
		listeReferent = new JList<String>(referents.toArray(new String[0]));
		
		// ecoute ce qui se passe ds panel listeReferent
		listeReferent.addListSelectionListener(this);
		
		// ajout de la liste des referent dans un panel a louest
		add(new JScrollPane(listeReferent), BorderLayout.WEST);
		
		 // BOUTON TRI PAR DROITE
		panelTri = new JPanel();
		BoxLayout bl = new BoxLayout(panelTri, BoxLayout.Y_AXIS);
		panelTri.setLayout(bl);
		
		add(panelTri, BorderLayout.EAST);
		triName = new JButton("Nom");
		triFirstname = new JButton("Prenom");
		triDefault = new JButton("id");
		triEmail = new JButton("email");
		triAge = new JButton("Age");
		
		panelTri.add(triDefault);
		panelTri.add(triName);
		panelTri.add(triFirstname);
		panelTri.add(triEmail);
		panelTri.add(triAge);

		// affichage de liste de contact
		contactVsisibleData = new DefaultListModel<>();
		contactVisible = new JList<>(contactVsisibleData);
		
		add(new JScrollPane(contactVisible));
		currentFilter = Contact.getFilter("all");
		currentSort = Contact.ID_SORT;
		isGold = Contact.getGold(true);
		ageFilter = Contact.filterAge(0);

		// mise en place des commandes on clicl event
		triDefault.addActionListener(this);
		triDefault.setActionCommand(TRI_ID_COMMAND);
		triName.addActionListener(this);
		triName.setActionCommand(TRI_NOM_COMMAND);
		triFirstname.addActionListener(this);
		triFirstname.setActionCommand(TRI_PRENOM_COMMAND);
		triEmail.addActionListener(this);
		triEmail.setActionCommand(TRI_EMAIL_COMMAND);
		triAge.addActionListener(this);
		triAge.setActionCommand(TRI_AGE_COMMAND);
		
		// ajout checkbox isGold
		checkGold = new JCheckBox("est gold");
		panel = new JPanel();
		//BoxLayout bb = new BoxLayout(panel, BoxLayout.X_AXIS);
		//panel.setLayout(bb);
		add(panel, BorderLayout.NORTH);
		panel.add(checkGold);
		
		ageTextFilter = new JTextField(10);
		panel.add(ageTextFilter);
		
		checkGold.addActionListener(this);
		checkGold.setActionCommand(GOLD);
		
		ageTextFilter.addActionListener(this);
		ageTextFilter.setActionCommand(AGE_FILTER);
		refreshList();
		// AJOUT SAUVEGARDE DANS UN FICHIER

	}
	private void refreshList() {
		contactVsisibleData.clear();
		contactFullData.stream()
			.filter(currentFilter)
			.sorted(currentSort)
			.filter(isGold)
			.filter(ageFilter)
			.forEach(c->contactVsisibleData.addElement(c));
		
		
	}
	@Override
	public void valueChanged(ListSelectionEvent e) {
		String choice = listeReferent.getSelectedValue();
		
		if (choice == null)  currentFilter = Contact.getFilter("all");
		else currentFilter = Contact.getFilter(choice);
		refreshList();
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		//String choice = 
		switch(e.getActionCommand()) {
			case TRI_ID_COMMAND: currentSort = Contact.ID_SORT;break;
			case TRI_NOM_COMMAND: currentSort = Contact.NOM_SORT;break;
			case TRI_PRENOM_COMMAND: currentSort = Contact.PRENOM_SORT; break;
			case TRI_EMAIL_COMMAND: currentSort = Contact.EMAIL_SORT; break;
			case TRI_AGE_COMMAND: currentSort = Contact.AGE_SORT; break;
			case GOLD: isGold = checkGold.isSelected() == true ? Contact.getGold(false): Contact.getGold(true); break;
			case AGE_FILTER: ageFilter = Contact.filterAge((Integer.parseInt(ageTextFilter.getText()))); break;
			default: currentSort = Contact.ID_SORT; break;
		}
		refreshList();
		
	}
	
	
	
}
