head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToManualLapseMainResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���K�[�����Ǘ��ҁE�蓮�������C�����X�|���X(WEB3AdminToManualLapseMainResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/20�@@�]�V�q(���u) �V�K�쐬
*/

package webbroker3.admintriggerorder.message;

import webbroker3.common.message.WEB3BackResponse;

/**
 * (�g���K�[�����Ǘ��ҁE�蓮�������C�����X�|���X)<BR>
 * �g���K�[�����Ǘ��ҁE�蓮�������C�����X�|���X<BR>
 * 
 * @@author �]�V�q
 * @@version 1.0
 */
public class WEB3AdminToManualLapseMainResponse extends WEB3BackResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_to_manual_lapse_main";
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200603170900L;
    
    /**
     * @@roseuid 441A08CD0157
     */
    public WEB3AdminToManualLapseMainResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AdminToManualLapseMainResponse(WEB3AdminToManualLapseMainRequest l_request)
    {
        super(l_request);
    }
}
@
