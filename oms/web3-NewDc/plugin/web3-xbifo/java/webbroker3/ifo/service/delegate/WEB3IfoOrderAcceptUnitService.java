head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoOrderAcceptUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP������tUnitService�C���^�[�t�F�C�X(WEB3IfoOrderAcceptUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/8 䈋� (���u) �V�K�쐬  
Revesion History : 2007/01/25 ���� (���u) �d�l�ύX ���f��605
*/
package webbroker3.ifo.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.ifo.data.HostFotypeOrderAcceptParams;

/**
 * (�敨OP������tUnitService)<BR>
 * �敨OP������tUnitService�C���^�[�t�F�C�X<BR>
 */
public interface WEB3IfoOrderAcceptUnitService extends Service 
{
   
   /**
    * (notify������t)<BR>
    * ������t�������s���B<BR>
    * 
    * @@param l_hostFotypeOrderAcceptParams - ������t�L���[Params�I�u�W�F�N�g
    * @@roseuid 4190C3F400C3
    */
   public void notifyOrderAccept(HostFotypeOrderAcceptParams l_hostFotypeOrderAcceptParams)
    throws WEB3BaseException;

   /**
    * (notify��t���ԊO)<BR>
    * ��t���ԊO�������s���B<BR>
    *
    * @@param l_hostFotypeOrderAcceptParams - (������t�L���[Params)<BR>
    * ������t�L���[Params�I�u�W�F�N�g
    */
   public void notifyOrderAcceptOvertime(HostFotypeOrderAcceptParams l_hostFotypeOrderAcceptParams)
   throws WEB3BaseException;
}
@
