head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.12.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPAssetTradingPowerService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ���Y�]�͏���ʕ\���T�[�r�X�N���X(WEB3TPAssetTradingPowerService.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/12/22 asano(SCS) �V�K�쐬
 */
package webbroker3.tradingpower.service.delegate;

import webbroker3.common.service.delegate.WEB3BusinessService;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (���Y�]�͏���ʕ\���T�[�r�X)<BR>
 * ���Y�]�͏���ʕ\���T�[�r�X�C���^�[�t�F�C�X�B<BR>
 * 
 * @@author asano(SCS)
 */
public interface WEB3TPAssetTradingPowerService extends WEB3BusinessService 
{
  
   /**
    * (execute)�@@
    * ���Y�]�͏���ʕ\���T�[�r�X���������{����B
    * @@param l_request
    * @@return webbroker3.common.message.WEB3GenResponse)
    * @@throws webbroker3.common.WEB3BaseException
    * @@roseuid 41B65BDF00CA
    */
   public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
