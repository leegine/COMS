head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.18.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXAskingCommonResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : FX�˗����ʃ��X�|���X(WEB3FXAskingCommonResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/19 ���z (���u) �V�K�쐬
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (FX�˗����ʃ��X�|���X) <BR>
 * FX�˗����ʃ��X�|���X�N���X <BR>
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3FXAskingCommonResponse extends WEB3GenResponse
{
    /**
     * (URL) <BR>
     * �ב֕ۏ؋��Ǘ��V�X�e���iGFT�j��URL <BR>
     */
    public String fxUrl;

    /**
     * (GFT�˗��d������) <BR>
     * GFT�˗��d���̖��� <BR>
     */
    public WEB3FXGftAskingTelegramUnit fxGftAskingTelegramUnit;

    /**
     * @@roseuid 41E7693500FA
     */
    public WEB3FXAskingCommonResponse()
    {
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3FXAskingCommonResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
}@
