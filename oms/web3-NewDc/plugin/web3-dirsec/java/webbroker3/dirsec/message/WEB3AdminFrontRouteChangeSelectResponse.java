head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.09.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFrontRouteChangeSelectResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�����o�H�֑ؑI�����X�|���X (WEB3AdminFrontRouteChangeSelectResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/17  �Ӑ� (���u) �d�l�ύX���f��No.116
*/
package webbroker3.dirsec.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁE�����o�H�֑ؑI�����X�|���X)<BR>
 * <BR>
 * �Ǘ��ҁE�����o�H�֑ؑI���T�[�r�X�i�I����ʕ\���j�̃��X�|���X�f�[�^�B<BR>
 * <BR>
 * @@author SCS.Sato
 * @@version 1.0
 */
public class WEB3AdminFrontRouteChangeSelectResponse extends WEB3GenResponse {
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_front_route_change_select";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * �i�����o�H���ꗗ�j<BR>
     * <BR>
     */
    public WEB3AdminFrontProcessNumberInfoUnit[] orderRouteInfoUnit;


    
    /**
     * @@roseuid 42FFFEF90300
     */
    public WEB3AdminFrontRouteChangeSelectResponse()
    {

    }

    /**
      * @@roseuid 
      * @@param l_request l_request
      */
    public WEB3AdminFrontRouteChangeSelectResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
