head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeBatoDisplayCommonResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �d�q���\�����ʃ��X�|���X(WEB3GentradeBatoDisplayCommonResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/15 ����(�r�q�`) �V�K�쐬
*/
package webbroker3.gentrade.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * �d�q���\�����ʃ��X�|���X
 */
public class WEB3GentradeBatoDisplayCommonResponse
    extends WEB3GenResponse
{

    /**
     * �ғ��`�F�b�N����<br />
     */
    public boolean isWorking;

    /**
     * URL<br />
     */
    public String url;

    /**
     * �n�b�V���l<br />
     */
    public String hashValue;


    /**
     * �f�t�H���g�R���X�g���N�^
     */
    public WEB3GentradeBatoDisplayCommonResponse() 
    {
    }
   
    /**
     * �R���X�g���N�^<br />
     * @@param WEB3GenRequest - ���N�G�X�g�f�[�^<br />
     * <br />
     * @@return WEB3GentradeBatoDisplayCommonResponse<br />
     * @@roseuid 423670A9017C
     */
    public WEB3GentradeBatoDisplayCommonResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }

}
@
