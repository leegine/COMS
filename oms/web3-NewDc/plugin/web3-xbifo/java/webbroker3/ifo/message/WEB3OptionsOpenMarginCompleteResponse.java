head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.21.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsOpenMarginCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V�����V�K�������������X�|���X(WEB3OptionsOpenMarginCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 ���o�� (���u) �V�K�쐬
*/

package webbroker3.ifo.message;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;

/**
 * (�����w���I�v�V�����V�K�������������X�|���X)<BR>
 * �����w���I�v�V�����V�K�������������X�|���X�N���X<BR>
 * @@author ���o��
 * @@version 1.0
 */
public class WEB3OptionsOpenMarginCompleteResponse extends WEB3GenResponse
{

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200406141508L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "options_openMarginComplete";

    /**
     * �X�V����
     */
    public java.util.Date lastUpdatedTimestamp;

    /**
     * ���ʔԍ�<BR>    
     * ���������h�c<BR>
     */
    public String orderActionId;
    
    /**
     * �f�t�H���g�R���X�g���N�^
     */
    public WEB3OptionsOpenMarginCompleteResponse()
    {
        
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3OptionsOpenMarginCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
