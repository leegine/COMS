head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.12.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOpenMarginChangeCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�����V�K���������X�|���X�N���X(WEB3FuturesOpenMarginChangeCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/21 ����(���u) �V�K�쐬
Revesion History : 2008/03/12 �����F�@@�d�l�ύX���f��829
*/
package webbroker3.ifo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�����w���敨�����V�K���������X�|���X)<BR>
 * �����w���敨�����V�K���������X�|���X�N���X
 * @@author ����
 * @@version 1.0
 */
public class WEB3FuturesOpenMarginChangeCompleteResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "futures_openMarginChangeComplete";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200407211330L;
    /**
     * (�X�V����)
     */
    public Date lastUpdatedTimestamp;
    
    /**
     * (���ʔԍ�)<BR>
     * ���������h�c<BR>
     */
    public String orderActionId;
    
    /**
     * (�A�������ݒ�t���O)<BR>
     * true�F�ݒ肠��@@�@@�@@false�F�ݒ�Ȃ�<BR>
     */
    public boolean succSettingFlag;

    /**
     * @@roseuid 40F7AE1101E4
     */
    public WEB3FuturesOpenMarginChangeCompleteResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3FuturesOpenMarginChangeCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
