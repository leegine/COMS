head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.49.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3SockPoolControlService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        :�@@(WEB3SockPoolControlService.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/08/06 ��(FLJ) �V�K�쐬
 */

package webbroker3.sockpoolctrl.service.delegate;

import webbroker3.common.*;
import webbroker3.common.message.*;
import webbroker3.common.service.delegate.*;

public interface WEB3SockPoolControlService
    extends WEB3BusinessService
{

    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
