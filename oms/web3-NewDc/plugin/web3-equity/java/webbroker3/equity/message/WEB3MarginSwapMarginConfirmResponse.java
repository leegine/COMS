head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginSwapMarginConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����������n�����m�F���X�|���X(WEB3MarginSwapMarginConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/16 ������ (���u) �V�K�쐬
Revesion History : 2004/12/13 �K�� (SRA) �C��
*/

package webbroker3.equity.message;

/**
 * �i�M�p����������n�����m�F���X�|���X�j�B<br>
 * <br>
 * �M�p����������n�����m�F���X�|���X�N���X
 * @@author ������
 * @@version 1.0
 */
public class WEB3MarginSwapMarginConfirmResponse extends WEB3MarginConfirmCommonResponse 
{
    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_swapMarginConfirm";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101617L;
    
    /**
     * (�������׈ꗗ)
     */
    public WEB3MarginContractUnit[] contractUnits;
    
    /**
     * (����ID)
     */
    public String orderId;
    
	/**
	 * (�C���T�C�_�[�x���\���t���O)<BR>
	 * true�F�x���\���v�@@�@@�@@false�F�x���\���s�v
	 */
	public boolean insiderWarningFlag;

    /**
     * (���ӕ����\���敪)<BR>
     * null�F���ӕ�����\���@@�@@3�F�a����s�����ӕ����\��<BR>
     */
    public String attentionObjectionType;
    
    /**
     * (�a����s���z)<BR>
     * �]�͂̕s�����z<BR>
     */
    public String accountBalanceInsufficiency;

    /**
     * @@roseuid 4140425501A1
     */
    public WEB3MarginSwapMarginConfirmResponse() 
    {
     
    }
    
    /**
     * (�R���X�g���N�^�B)<BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3MarginSwapMarginConfirmResponse(WEB3MarginSwapMarginConfirmRequest l_request)
    {
        super(l_request);
    }
}
@
