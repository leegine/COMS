head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.34.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoOfferService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  IPO�w���\���T�[�r�X�C���^�t�F�C�X(WEB3IpoOfferService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 �m�a �V�K�쐬
*/
package webbroker3.ipo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
* ( IPO�w���\���T�[�r�X�C���^�t�F�C�X)<BR>
* 
* @@author �m�a
* @@version 1.0
*/
public interface WEB3IpoOfferService extends WEB3BusinessService 
{
    
     /**
     * IPO�w���\�����������{����B
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40DA5B3A038E
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
