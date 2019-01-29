head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginCloseMarginCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  �M�p����ԍϒ����������X�|���X�N���X(WEB3MarginCloseMarginCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/9/16 ������ (���u) �V�K�쐬
Revesion History : 2004/12/13 �K�� (SRA) �C��
Revesion History : 2007/06/13 ���g (���u) ���f��No.1167
*/
package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;

/**
 * �i�M�p����ԍϒ����������X�|���X�j�B<br>
 * <br>
 * �M�p����ԍϒ����������X�|���X�N���X
 * @@version 1.0
 */
public class WEB3MarginCloseMarginCompleteResponse extends WEB3GenResponse 
{
    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_closeMarginComplete";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101643L;      
    /**
     * (�X�V����)<BR>
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
     * (�����L������)<BR>
     * �����L������
     */
    public Date expirationDate;

    /**
     * @@roseuid 414047D802C2
     */
    public WEB3MarginCloseMarginCompleteResponse() 
    {
  
    }
    
    /**
     * (�R���X�g���N�^�B)<BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3MarginCloseMarginCompleteResponse(WEB3MarginCloseMarginCompleteRequest l_request)
    {
        super(l_request);
    }
}
@
