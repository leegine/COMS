head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoCancelAcceptedUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݓ������tUnitService  (WEB3RuitoCancelAcceptedUnitService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14  �����F (���u) �V�K�쐬
*/
package webbroker3.xbruito.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoOrderUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.xbruito.WEB3RuitoAcceptedDecisionInterceptor;

/**
 * �ݓ������t�P���T�[�r�X�C���^�t�F�[�X<BR>
 * <BR>
 * ��������P�����Ƃ̎�t���������{����B<BR>
 */
public interface WEB3RuitoCancelAcceptedUnitService extends Service 
{
   
   /**
    * �ݓ������t���������������Ȃ��B<BR>
    * @@param l_ruitoOrderUnit - �ݓ������P�� <BR>
    * @@param l_ruitoAcceptedDecisionInterceptor - �ݓ���t�m��C���^�Z�v�^<BR>
    * @@throws webbroker3.common.WEB3BaseException
    * @@roseuid 408887A103B8
    */
    public void notifyCancelAcceptedComplete(RuitoOrderUnit l_ruitoOrderUnit,
                                           WEB3RuitoAcceptedDecisionInterceptor 
                                           l_ruitoAcceptedDecisionInterceptor) 
                                           throws WEB3BaseException;
   
   /**
    * �ݓ������t���s�����������Ȃ��B<BR>
    * @@param l_ruitoOrderUnit - �ݓ������P�� <BR>
    * @@param l_ruitoAcceptedDecisionInterceptor - �ݓ���t�m��C���^�Z�v�^<BR>
    * @@throws webbroker3.common.WEB3BaseException
    * @@roseuid 408887A103BA
    */
    public void notifyCancelAcceptedFail(RuitoOrderUnit l_ruitoOrderUnit, 
                                        WEB3RuitoAcceptedDecisionInterceptor 
                                        l_ruitoAcceptedDecisionInterceptor) 
                                        throws WEB3BaseException;
}
@
