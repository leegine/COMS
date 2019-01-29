head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginOpenMarginCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����V�K�������������X�|���X(WEB3MarginOpenMarginCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/17 ������ (���u) �V�K�쐬
Revesion History : 2004/12/10 �K�� (SRA) �C��
Revesion History : 2006/12/25 �����F (���u) ���f�� 1085
Revesion History : 2007/06/13 ���g (���u) ���f��No.1167
*/

package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;

/**
 * �i�M�p����V�K�������������X�|���X�j�B<br>
 * <br>
 * �M�p����V�K�������������X�|���X�N���X
 * @@author ������
 * @@version 1.0
 */
public class WEB3MarginOpenMarginCompleteResponse extends WEB3GenResponse 
{
    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_openMarginComplete";

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
     * ����ID�B
     */
    public String orderActionId;
    
    /**
     * (�C���T�C�_�[�x���\���t���O)<BR>
     * true�F�x���\���v�@@<BR>
     * false�F�x���\���s�v <BR>
     */
    public boolean insiderWarningFlag;

    /**
     * (�s��R�[�h)<BR>
     * �s��R�[�h
     */
    public String marketCode;

    /**
     * (�����L������)<BR>
     * �����L������
     */
    public Date expirationDate;

    /**
     * @@roseuid 4140477D0221
     */
    public WEB3MarginOpenMarginCompleteResponse() 
    {
     
    }
    
    /**
     * (�R���X�g���N�^�B)<BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3MarginOpenMarginCompleteResponse(WEB3MarginOpenMarginCompleteRequest l_request)
    {
        super(l_request);
    }
}
@
