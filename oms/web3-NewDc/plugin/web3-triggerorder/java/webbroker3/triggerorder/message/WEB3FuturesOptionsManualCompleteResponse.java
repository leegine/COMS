head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.49.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3FuturesOptionsManualCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�蓮�����������X�|���X(WEB3FuturesOptionsManualCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/17�@@�]�V�q(���u) �V�K�쐬
*/

package webbroker3.triggerorder.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�敨OP�蓮�����������X�|���X)<BR>
 * �敨OP�蓮�����������X�|���X�N���X<BR>
 * 
 * @@author �]�V�q
 * @@version 1.0
 */
public class WEB3FuturesOptionsManualCompleteResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "futures_options_manual_complete";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200602162240L;
    
    /**
     * (�X�V����)<BR>
     * �X�V����<BR>
     */
    public Date lastUpdatedTimestamp;
    
    /**
     * (�敨OP�蓮����Unit)<BR>
     * �敨OP�蓮����Unit<BR>
     */
    public WEB3FuturesOptionsManualUnit manualUnits[];
    
    /**
     * �R���X�g���N�^<BR>
     * @@roseuid 43F488920271
     */
    public WEB3FuturesOptionsManualCompleteResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3FuturesOptionsManualCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }    

}
@
