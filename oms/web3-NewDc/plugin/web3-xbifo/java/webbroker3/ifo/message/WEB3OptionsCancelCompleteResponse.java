head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.23.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsCancelCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V������������������X�|���X(WEB3OptionsCancelCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 羉s (���u) �V�K�쐬
Revesion History : 2008/03/12 ����(���u) ���f�� 830
*/

package webbroker3.ifo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;

/**
 * (�����w���I�v�V������������������X�|���X)<BR>
 * �����w���I�v�V������������������X�|���X�N���X<BR>
 * @@author 羉s
 * @@version 1.0
 */
public class WEB3OptionsCancelCompleteResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "options_cancelComplete";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200406102008L;

    /**
     * (�X�V����)<BR>
     */
    public Date lastUpdatedTimestamp;

    /**
     * (���ʔԍ�)<BR>
     * ���������h�c<BR>
     */
    public String orderActionId;
    
    /**
     * (�A�������ݒ�t���O)<BR>
     * �A�������ݒ�t���O<BR>
     * true�F�ݒ肠��@@�@@�@@false�F�ݒ�Ȃ�<BR>
     */
    public boolean succSettingFlag;

    /**
     * �f�t�H���g�R���X�g���N�^
     */
    public WEB3OptionsCancelCompleteResponse()
    {
        
    }

    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3OptionsCancelCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
