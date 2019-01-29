head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginSwapMarginCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����������n�����������X�|���X(WEB3MarginSwapMarginCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/17 ������ (���u) �V�K�쐬
Revesion History : 2004/12/13 �K�� (SRA) �C��
*/

package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;

/**
 * �i�M�p����������n�����������X�|���X�j�B<br>
 * <br>
 * �M�p����������n�����������X�|���X�N���X
 * @@author ������
 * @@version 1.0
 */
public class WEB3MarginSwapMarginCompleteResponse extends WEB3GenResponse 
{
    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_swapMarginComplete";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101617L;
    
    /**
     * (�X�V����)
     */
    public Date lastUpdatedTimestamp;
    
    /**
     * (���ʔԍ�)<BR>
     * <BR>
     * �����h�c<BR>
     */
    public String orderActionId;
    
	/**
	 * (�C���T�C�_�[�x���\���t���O)<BR>
	 * true�F�x���\���v�@@�@@�@@false�F�x���\���s�v
	 */
	public boolean insiderWarningFlag;
    
    /**
     * @@roseuid 414042550010
     */
    public WEB3MarginSwapMarginCompleteResponse() 
    {
     
    }
    
    /**
     * (�R���X�g���N�^�B)<BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3MarginSwapMarginCompleteResponse(WEB3MarginSwapMarginCompleteRequest l_request)
    {
        super(l_request);
    }    
}
@
