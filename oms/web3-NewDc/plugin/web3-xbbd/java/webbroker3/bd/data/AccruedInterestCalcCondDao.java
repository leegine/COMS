head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	AccruedInterestCalcCondDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.bd.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.bd.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.*;

/** 
 * {@@link AccruedInterestCalcCondDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link AccruedInterestCalcCondRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
 * �N���C�A���g�R�[�h�ɂ����ĕK�v�Ƃ���鋤�ʂ̃f�[�^�I�y���[�V�������������Ă��܂��B 
 * <p> 
 *     <li> �V�������R�[�h�ɑ΂���ӂ̎�L�[�l�܂��̓I�u�W�F�N�g���쐬 </li> 
 *     <li> �O���L�[���烌�R�[�h������ </li> 
 *     <li> �O���L�[�̊֌W�ɂ���I�u�W�F�N�g������ </li> 
 *     <li> �C���f�b�N�X���������̃f�[�^�x�[�X�X�L�[�}�ɃN�G�������s </li> 
 * <p> 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.xtrade.kernel.data.DataAccessObject 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AccruedInterestCalcCondPK 
 * @@see AccruedInterestCalcCondRow 
 */
public class AccruedInterestCalcCondDao extends DataAccessObject {


  /** 
   * ����{@@link AccruedInterestCalcCondDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private AccruedInterestCalcCondRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link AccruedInterestCalcCondRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link AccruedInterestCalcCondDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link AccruedInterestCalcCondDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link AccruedInterestCalcCondRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof AccruedInterestCalcCondRow )
                return new AccruedInterestCalcCondDao( (AccruedInterestCalcCondRow) row );
            throw new java.lang.IllegalArgumentException( "Not a AccruedInterestCalcCondRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link AccruedInterestCalcCondRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link AccruedInterestCalcCondRow}�I�u�W�F�N�g 
    */
    protected AccruedInterestCalcCondDao( AccruedInterestCalcCondRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link AccruedInterestCalcCondRow}�I�u�W�F�N�g���擾���܂��B
   */
    public AccruedInterestCalcCondRow getAccruedInterestCalcCondRow() {
        return row;
    }


  /** 
   * �w���{@@link AccruedInterestCalcCondRow}�I�u�W�F�N�g����{@@link AccruedInterestCalcCondDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link AccruedInterestCalcCondRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link AccruedInterestCalcCondDao}�擾�̂��߂Ɏw���{@@link AccruedInterestCalcCondRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link AccruedInterestCalcCondDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static AccruedInterestCalcCondDao forRow( AccruedInterestCalcCondRow row ) throws java.lang.IllegalArgumentException {
        return (AccruedInterestCalcCondDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link AccruedInterestCalcCondRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link AccruedInterestCalcCondRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link AccruedInterestCalcCondPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link AccruedInterestCalcCondParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( AccruedInterestCalcCondRow.TYPE );
    }


  /** 
   * {@@link AccruedInterestCalcCondRow}����ӂɓ��肷��{@@link AccruedInterestCalcCondPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link AccruedInterestCalcCondRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link AccruedInterestCalcCondParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link AccruedInterestCalcCondPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static AccruedInterestCalcCondPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with non-long value components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link AccruedInterestCalcCondRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_accruedInterestCalcType �����Ώۂł���p_accruedInterestCalcType�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link AccruedInterestCalcCondRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static AccruedInterestCalcCondRow findRowByPk( String p_accruedInterestCalcType ) throws DataFindException, DataQueryException, DataNetworkException {
        AccruedInterestCalcCondPK pk = new AccruedInterestCalcCondPK( p_accruedInterestCalcType );
        return findRowByPk( pk );
    }


  /** 
   * �w���AccruedInterestCalcCondPK�I�u�W�F�N�g����{@@link AccruedInterestCalcCondRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����AccruedInterestCalcCondPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link AccruedInterestCalcCondRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static AccruedInterestCalcCondRow findRowByPk( AccruedInterestCalcCondPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (AccruedInterestCalcCondRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String)}�����{@@link #forRow(AccruedInterestCalcCondRow)}���g�p���Ă��������B 
   */
    public static AccruedInterestCalcCondDao findDaoByPk( String p_accruedInterestCalcType ) throws DataFindException, DataQueryException, DataNetworkException {
        AccruedInterestCalcCondPK pk = new AccruedInterestCalcCondPK( p_accruedInterestCalcType );
        AccruedInterestCalcCondRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(AccruedInterestCalcCondPK)}�����{@@link #forRow(AccruedInterestCalcCondRow)}���g�p���Ă��������B 
   */
    public static AccruedInterestCalcCondDao findDaoByPk( AccruedInterestCalcCondPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        AccruedInterestCalcCondRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


      // (this object has no foreign keys)


    //===========================================================================
    //
    // Find Rows or Daos given index values
    //
    //===========================================================================

    //------------------------------------------------------
    // Find Rows given unique index values
    //------------------------------------------------------


  /** 
   * p_accruedInterestCalcType, and �ɂĎw��̒l�����ӂ�{@@link AccruedInterestCalcCondRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_accruedInterestCalcType �����Ώۂł���p_accruedInterestCalcType�t�B�[���h�̒l
   * 
   * @@return �����w���p_accruedInterestCalcType, and �̒l�ƈ�v����{@@link AccruedInterestCalcCondRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static AccruedInterestCalcCondRow findRowByAccruedInterestCalcType( String p_accruedInterestCalcType ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            AccruedInterestCalcCondRow.TYPE,
            "accrued_interest_calc_type=?",
            null,
            new Object[] { p_accruedInterestCalcType } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (AccruedInterestCalcCondRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query AccruedInterestCalcCondDao.findRowsByAccruedInterestCalcType(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByAccruedInterestCalcType(String)}�����{@@link #forRow(AccruedInterestCalcCondRow)}���g�p���Ă��������B 
   */
    public static AccruedInterestCalcCondDao findDaoByAccruedInterestCalcType( String p_accruedInterestCalcType ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByAccruedInterestCalcType( p_accruedInterestCalcType ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
