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
filename	WEB3EquityCancelCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ����������������������X�|���X(WEB3EquityCancelOrderCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/17 ���_�� (���u) �V�K�쐬
*/

package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;

/**
 * �i����������������������X�|���X�j�B<BR>
 * <BR>
 * ������������������������@@���X�|���X�f�[�^�N���X
 * @@version 1.0
 */
public class WEB3EquityCancelCompleteResponse extends WEB3GenResponse
{

    /**
     * (�X�V����)<BR>
     * <BR>
     * ������t����<BR>
     */
    public Date lastUpdatedTimestamp;

    /**
     * (���ʔԍ�)<BR>
     * <BR>
     * ����ID<BR>
     */
    public String orderActionId;

    /**
     * (�A�������ݒ�t���O)<BR>
     * <BR>
     * true�F�ݒ肠��@@�@@�@@false�F�ݒ�Ȃ�<BR>
     */
    public boolean succSettingFlag;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_cancel_complete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200405151153L;

    /**
     * @@roseuid 40AC536601B8
     */
    public WEB3EquityCancelCompleteResponse()
    {

    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     * @@roseuid 405023760250
     */
    public WEB3EquityCancelCompleteResponse(WEB3EquityCancelCompleteRequest l_request)
    {
        super(l_request);
    }
        
}
@
