head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.11.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPOrixVantiveService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : Vantive�A�g�T�[�r�X�N���X(WEB3TPOrixVantiveService.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2005/03/18 Matsumoto(SRA) �V�K�쐬
 */
package webbroker3.tradingpower.service.delegate;

import webbroker3.common.service.delegate.WEB3BackBusinessService;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.WEB3BaseException;

/**
 * (Vantive�A�g�T�[�r�X)<BR>
 * Vantive�A�g�T�[�r�X�C���^�[�t�F�C�X�B<BR>
 * 
 * @@author Matsumoto(SRA)
 */
public interface WEB3TPOrixVantiveService extends WEB3BackBusinessService 
{
  
   /**
    * (execute)�@@
    * Vantive�A�g�T�[�r�X���������{����B
    * @@param l_request
    * @@return webbroker3.common.message.WEB3BackResponse)
    * @@throws webbroker3.common.WEB3BaseException
    */
   public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException;
}
@
