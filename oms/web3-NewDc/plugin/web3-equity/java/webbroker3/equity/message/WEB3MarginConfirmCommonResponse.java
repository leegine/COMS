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
filename	WEB3MarginConfirmCommonResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����m�F���ʃ��X�|���X(WEB3MarginConfirmCommonResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/16 ������ (���u) �V�K�쐬
*/

package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * �i�M�p����m�F���ʃ��X�|���X�j�B<br>
 * <br>
 * �M�p����m�F���ʃ��X�|���X�N���X
 * @@author ������
 * @@version 1.0
 */
public class WEB3MarginConfirmCommonResponse extends WEB3GenResponse 
{
    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_confirmCommon";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101754L;    
    /**
     * (�m�F��������)<BR>
     * <BR>
     * �������N�G�X�g�ő��M����l<BR>
     */
    public Date checkDate;
    
    /**
     * (�T�Z��n���)<BR>
     * <BR>
     * �V�K���F�@@�T�Z�������ݒ�<BR>
     * �ԍρF�@@�T�Z���ϑ��v�����ݒ�<BR>
     * �������n�F�@@�T�Z��n�����ݒ�<BR>
     */
    public String estimatedPrice;
    
    /**
     * (����I���x���s��R�[�h�ꗗ)<BR>
     * <BR>
     * ����I���x��������\������s��R�[�h�̈ꗗ<BR>
     */
    public String[] messageSuspension;
    
    /**
     * @@roseuid 41403F6F0118
     */
    public WEB3MarginConfirmCommonResponse() 
    {
     
    }
    
    /**
     * (�R���X�g���N�^�B)<BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3MarginConfirmCommonResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
