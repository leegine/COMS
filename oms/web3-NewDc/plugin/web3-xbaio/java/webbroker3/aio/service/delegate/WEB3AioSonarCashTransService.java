head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.18.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioSonarCashTransService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : SONAR���o���T�[�r�X(WEB3AioSonarCashTransService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 ���� (���u) �V�K�쐬
                   2004/10/26 ���z (���u) ���r���[
*/

package webbroker3.aio.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;

/**
 * (SONAR���o���T�[�r�X)<BR>
 * SONAR���o���T�[�r�X�C���^�[�t�F�C�X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0  
 */
public interface WEB3AioSonarCashTransService extends WEB3BackBusinessService 
{
    
    /**
     * SONAR���o���T�[�r�X�������s���B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 40FF6E070138
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) 
        throws WEB3BaseException;
}
@
