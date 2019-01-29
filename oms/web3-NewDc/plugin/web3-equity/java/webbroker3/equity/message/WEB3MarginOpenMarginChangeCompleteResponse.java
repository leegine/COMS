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
filename	WEB3MarginOpenMarginChangeCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  �M�p�����������_�V�K���������X�|���X�N���X(WEB3MarginOpenMarginChangeCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/9/15 ������ (���u) �V�K�쐬
Revesion History : 2004/12/10 �K�� (SRA) �C��
Revesion History : 2007/06/13 ���g (���u) ���f��No.1167
*/
package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;

/**
 * �i�M�p�����������_�V�K���������X�|���X�j�B<br>
 * <br>
 * �M�p�����������_�V�K���������X�|���X�N���X
 * @@version 1.0
 */
public class WEB3MarginOpenMarginChangeCompleteResponse extends WEB3GenResponse 
{
    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_openMarginChangeComplete";

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
     * �����h�c
     */
    public String orderActionId;
    
    /**
     * (�C���T�C�_�[�x���\���t���O)<BR>
     * true�F�x���\���v�@@�@@�@@false�F�x���\���s�v
     */
    public boolean insiderWarningFlag;
    
    /**
     * (�A�������ݒ�t���O)<BR>
     * true�F�ݒ肠��@@�@@�@@false�F�ݒ�Ȃ�<BR>
     */
    public boolean succSettingFlag;

    /**
     * (�����L������)<BR>
     * �����L������
     */
    public Date expirationDate;

    /**
     * @@roseuid 4140453902A9
     */
    public WEB3MarginOpenMarginChangeCompleteResponse() 
    {
     
    }
    
    /**
     * (�R���X�g���N�^�B)<BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3MarginOpenMarginChangeCompleteResponse(WEB3MarginOpenMarginChangeCompleteRequest l_request)
    {
        super(l_request);
    }
}
 @
